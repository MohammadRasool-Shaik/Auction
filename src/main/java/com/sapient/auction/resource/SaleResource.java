package com.sapient.auction.resource;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapient.auction.exception.AuthenticationException;
import com.sapient.auction.model.Sale;
import com.sapient.auction.service.ISaleService;

/**
 * TODO - Pending validations Controls the flow of the requests for creating
 * auctions and getting auction details
 * 
 * @author mshai9
 *
 */

@Path("/sales")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class SaleResource {

	private static Logger logger = Logger.getLogger(SaleResource.class.getName());

	@Autowired
	public ISaleService saleService;

	/**
	 * Create or update an existing Sale URL sample :
	 * http://localhost:8080/sales/create JSON Request { "basePrice": 10.50,
	 * "startDate" : 123456722, "endDate" : 20292902211, "product" :
	 * {"productId" : 3}, "emailId" : "", "version" :1, "createdBy" : "ADMIN",
	 * "updatedBy" : "ADMIN", "status" : "A"
	 * 
	 * }
	 * 
	 * @param sale
	 * @return
	 * @throws Exception
	 */

	@RolesAllowed("SELLER")
	@POST
	public Sale createSale(Sale sale) throws Exception {
		return saleService.saveOrUpdate(sale);
	}

	/**
	 * Fetch sale details by the sale ID URL smaple :
	 * http://localhost:8080/sales/12
	 * 
	 * @param saleId
	 * @return
	 * @throws Exception
	 */
	@RolesAllowed("SELLER")
	@GET
	@Path("/{saleId}")
	public Sale findBySaleId(@PathParam("saleId") Integer saleId) throws Exception {
		Sale sale = saleService.findBySaleId(saleId);
		if (sale == null) {
			throw new AuthenticationException("No resource found by this sale ID");
		}
		return sale;
	}

	/**
	 * Fetch all active sale for all existing users.
	 * 
	 * @return
	 * @throws Exception
	 */
	@RolesAllowed("SELLER")
	@GET
	public List<Sale> fetchAllSales() {
		return saleService.fetchAllSales();
	}

	@RolesAllowed("SELLER")
	@GET
	@Path("/active")
	public List<Sale> getActiveSales() throws Exception {
		return saleService.getActiveSales();
	}
}
