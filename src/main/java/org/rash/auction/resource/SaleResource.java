package org.rash.auction.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rash.auction.dto.SaleDTO;
import org.rash.auction.exception.NotFoundException;
import org.rash.auction.service.ISaleService;
import org.rash.auction.util.JWTPrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

/**
 * TODO - Pending validations Controls the flow of the requests for creating auctions and getting auction details
 *
 * @author mshai9
 */

@Path("/sales")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SaleResource {

    private static final Logger logger = LogManager.getLogger(SaleResource.class);

    @Autowired
    public ISaleService saleService;

    /**
     * Create or update an existing Sale URL sample : http://localhost:8080/sales/create JSON Request { "saleId": 4, "basePrice": 12.5, "startDate": 123456722, "endDate":
     * 20292902211, "productName": "SSD T-Shirt", "productDescription": "Color- Black, Size-L, For-Men ", "category": null, "photo": null, "responseStatus": null, "email":
     * "email4@mail.com" }
     *
     * @param sale
     * @return SaleDTO
     * @throws Exception
     */

    @RolesAllowed("SELLER")
    @POST
    @Path("/create")
    public SaleDTO createSale(@Valid SaleDTO saleDto, @Context SecurityContext securityContext) {
        logger.debug("Creating sale with details :" + saleDto);
        JWTPrincipal userPrincipal = (JWTPrincipal) securityContext.getUserPrincipal();
        return saleService.saveOrUpdate(saleDto, userPrincipal.getName());
    }

    /**
     * Fetch sale details by the sale ID URL : http://localhost:8080/sales/12
     *
     * @param saleId
     * @return
     * @throws Exception
     */
    @RolesAllowed({"SELLER", "ADMIN", "BUYER"})
    @GET
    @Path("/{saleId}")
    public SaleDTO findBySaleId(@PathParam("saleId") Integer saleId) {
        SaleDTO saleDTO = saleService.findBySaleId(saleId);
        if (saleDTO == null) {
            logger.error("No sale was found for the sale id :" + saleId);
            throw new NotFoundException();
        }
        return saleDTO;
    }

    /**
     * GET method to fetch all sales which are active - http://localhost:8080/sales/active
     *
     * @return
     * @throws Exception
     */
    @RolesAllowed({"SELLER", "ADMIN", "BUYER"})
    @GET
    @Path("/active")
    public List<SaleDTO> getActiveSales() {
        return saleService.getActiveSales();
    }
}
