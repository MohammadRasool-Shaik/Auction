package org.rash.auction.util;

import org.rash.auction.dto.BidDTO;
import org.rash.auction.dto.SaleDTO;
import org.rash.auction.enumerator.SaleStatus;
import org.rash.auction.exception.ValidationException;
import org.rash.auction.model.Bid;
import org.rash.auction.model.Sale;
import org.rash.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/**
 * Util class to convert models to entity and entity to models
 *
 * @author jyengk
 */

@Component
public class EntityModelAssembler {

    @Value("${auction.datetime.format}")
    private String defaultDateTimeFormat;

    @Value("${auction.date.format}")
    private String defaultDateFormat;

    @Autowired
    private AuctionDateUtil auctionDateUtil;

    private Sale convertSaleDtoToEntity(SaleDTO dto) throws ParseException {
        Sale entity = new Sale();
        entity.setBasePrice(dto.getBasePrice());
        entity.setCategory(dto.getCategory());
        entity.setEndDate(auctionDateUtil.convertToDefaultDateFormat(defaultDateTimeFormat, dto.getEndDate()));
        entity.setStartDate(auctionDateUtil.convertToDefaultDateFormat(defaultDateTimeFormat, dto.getStartDate()));
        entity.setProductName(dto.getProductName());
        entity.setCreatedDate(new Date());
        entity.setUpdatedDate(new Date());
        entity.setProductDescription(dto.getProductDescription());
        entity.setCategory(dto.getCategory());
        entity.setProductImage(dto.getProductImage());
        if (entity.getStartDate().compareTo(new Date()) > 0) {
            entity.setSaleStatus(SaleStatus.A);
        }
        User user = new User();
        entity.setUser(user);
        return entity;
    }

    /**
     * Move to DTO converter
     *
     * @param entity
     * @return
     */
    public SaleDTO convertSaleEntityToDto(Sale entity) {
        SaleDTO dto = new SaleDTO();
        dto.setSaleId(entity.getSaleId());
        dto.setBasePrice(entity.getBasePrice());
        dto.setCategory(entity.getCategory());
        dto.setStartDate(auctionDateUtil.convertDateToString(defaultDateTimeFormat, entity.getStartDate()));
        dto.setEndDate(auctionDateUtil.convertDateToString(defaultDateTimeFormat, entity.getEndDate()));
        dto.setProductDescription(entity.getProductDescription());
        dto.setProductName(entity.getProductName());
        return dto;
    }

    public Sale buildSaleEntity(SaleDTO saleDto, User user) {
        Sale sale = null;
        try {
            sale = convertSaleDtoToEntity(saleDto);
        } catch (ParseException ex) {
            throw new ValidationException("Date format should be in yyyy-MM-dd format");
        }

        sale.setUser(user);
        return sale;
    }

    public Bid buildBidEntity(BidDTO bidDto, Sale sale, User user) {
        Bid bid = new Bid();
        bid.setAmount(bidDto.getAmount());
        bid.setSale(sale);
        bid.setTime(new Date());
        bid.setUser(user);
        return bid;
    }

}
