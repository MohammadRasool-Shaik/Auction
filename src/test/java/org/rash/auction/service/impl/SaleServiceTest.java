package org.rash.auction.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rash.auction.dto.SaleDTO;
import org.rash.auction.enumerator.SaleStatus;
import org.rash.auction.enumerator.UserRole;
import org.rash.auction.enumerator.UserStatus;
import org.rash.auction.model.Sale;
import org.rash.auction.model.User;
import org.rash.auction.repository.SaleRepository;
import org.rash.auction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.rash.auction.ApplicationBootStart;
import org.rash.auction.model.Address;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBootStart.class)
public class SaleServiceTest {
	
	@Autowired
	@InjectMocks
	public SaleService saleService;
	
	@Mock
	public SaleRepository saleRepository;
	
	@Mock
	public UserRepository userRepository;
	
	public SaleDTO saleDto;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
		saleDto = new SaleDTO();
		saleDto.setBasePrice(12.20);
		saleDto.setCategory("TEST_CATEGORY");
		saleDto.setEndDate("2017-12-01");
		saleDto.setProductImage(null);
		saleDto.setProductDescription("Sample product description");
		saleDto.setProductName("Product Name");
		saleDto.setStartDate("2017-02-12");
	}
	
	@Test
	public void testSuccessfulSaveOrUpdate(){
		when(userRepository.findByEmail(any(String.class))).thenReturn(getUser());
		when(saleRepository.save(any(Sale.class))).thenReturn(getSale());
		SaleDTO responseSaleDto = saleService.saveOrUpdate(saleDto, "user@gmail.com");
		assertNotNull(responseSaleDto);
	}
	
	private User getUser(){
		User user = new User();
		user.setAddress(new Address());
		user.setDisplayName("TEST_USER");
		user.setEmail("sample@testemail.com");
		user.setStatus(UserStatus.A);
		user.setUserRole(UserRole.B);
		return user;
	}
	
	private Sale getSale(){
		Sale sale = new Sale();
		sale.setSaleId(1001);
		sale.setBasePrice(12.20);
		sale.setCategory("TEST_CATEGORY");
		sale.setCreatedBy("TEST_SELLER");
		sale.setSaleStatus(SaleStatus.A);
		sale.setUser(getUser());
		sale.setEndDate(new Date());
		sale.setStartDate(new Date());
		return sale;
	}
	
	
	
	
	
	

}
