package com.sapient.auction.Auction;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.sapient.auction.App;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest("server.port=8080")
public class AppTest {
	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void health() {
		ResponseEntity<List> entity = restTemplate.getForEntity("http://localhost:8080/sales/active", List.class);

	}
}
