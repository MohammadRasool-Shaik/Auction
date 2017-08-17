package org.rash.auction.resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rash.auction.dto.BidDTO;
import org.rash.auction.service.IBidService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Controls the flow of the requests for placing bids and getting bid details
 *
 * @author team 3
 */
@Path("/bids")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class BidResource {

    private static final Logger logger = LogManager.getLogger(BidResource.class);

    @Autowired
    public IBidService bidService;

    /**
     * Create or update an existing Bid URL sample : http://localhost:8080/bids/place JSON Request { "basePrice": 10.50, "startDate" : 123456722, "endDate" : 20292902211, "product"
     * : {"productId" : 3}, "emailId" : "", "version" :1, "createdBy" : "ADMIN", "updatedBy" : "ADMIN", "status" : "A"
     * <p>
     * }
     *
     * @param sale
     * @return @
     */

    @RolesAllowed({"SELLER", "BUYER"})
    @POST
    @Path("/place")
    public BidDTO saveOrUpdate(@Valid BidDTO bidDto) {
        logger.debug("The bid dto being saved is :" + bidDto);
        return bidService.saveOrUpdate(bidDto);
    }

    /**
     * Fetch maximum bids from the service
     *
     * @return @
     */
    @RolesAllowed({"ADMIN", "SELLER", "BUYER"})
    @GET
    @Path("/max")
    public BidDTO getMaxBidForSale(@QueryParam("saleId") Integer saleId) {
        logger.debug("Finding the maximum bid for the sale id :" + saleId);
        return bidService.getMaxBidForSale(saleId);
    }

}
