/**
 * 
 */
package com.ps.microservice.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.microservice.domain.Country;
import com.ps.microservice.domain.League;
import com.ps.microservice.model.StandingRequest;

/**
 * @author sapient_a
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class TeamStandingServiceTest {
	
	@Autowired
	private TeamStandingService teamStandingService;
	
	@Test
	public void testGetCountry() throws Exception {
		StandingRequest request = new StandingRequest("England", "Championship", "Manchester City");
		Country country = null;
		try {
			country = teamStandingService.getCountry(request);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getMessage());
		}
		Assert.assertNotNull(country);
		Assert.assertEquals("England", country.getCountryName());
	}
	
	
	@Test
	public void testGetLeague() throws Exception {
		StandingRequest request = new StandingRequest("England", "Championship", "Manchester City");
		League league = null;
		String countryId = "41";
		try {
			league = teamStandingService.getLeague(countryId, request);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getMessage());
		}
		Assert.assertNotNull(league);
		Assert.assertEquals("England", league.getLeagueName());
	}	
		

}
