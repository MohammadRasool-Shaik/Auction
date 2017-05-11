package com.sapient.auction.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.auction.ApplicationBootStart;
import com.sapient.auction.dto.SaleDTO;
import com.sapient.auction.enumerator.SaleStatus;
import com.sapient.auction.enumerator.UserRole;
import com.sapient.auction.enumerator.UserStatus;
import com.sapient.auction.model.Address;
import com.sapient.auction.model.Sale;
import com.sapient.auction.model.User;
import com.sapient.auction.repository.SaleRepository;
import com.sapient.auction.repository.UserRepository;
import com.sapient.auction.service.ISaleService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationBootStart.class)
public class SaleServiceTest {
	
	@Autowired
	@InjectMocks
	public ISaleService saleService;
	
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
//		Sale responseSaleDto = saleService.saveOrUpdate(saleDto, "user@gmail.com");
//		assertNotNull(responseSaleDto);
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
