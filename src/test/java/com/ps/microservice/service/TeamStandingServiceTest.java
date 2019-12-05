/**
 * 
 */
package com.ps.microservice.service;

import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.microservice.domain.Country;
import com.ps.microservice.domain.League;
import com.ps.microservice.model.StandingRequest;
import com.ps.microservice.model.StandingResponse;

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
	public void testTeamStanding()  {
		StandingRequest request = new StandingRequest("England", "Championship", "West Brom");
		StandingResponse response = null;
		try {
			response = teamStandingService.getTeamStanding(request);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getMessage());
		}
		Assert.assertNotNull(response);
	}	
	
	
	@Test
	public void testGetCountry()  {
		String countryName = "England";
		Country country = null;
		try {
			country = teamStandingService.getCountryByName(countryName);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getMessage());
		}
		Assert.assertNotNull(country);
		Assert.assertEquals(countryName, country.getCountryName());
	}
	
	
	@Test
	public void testGetLeague() {
		League league = null;
		String countryId = "41";
		String leagueName = "Championship";
		try {
			league = teamStandingService.getLeagueByName(countryId, leagueName);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getMessage());
		}
		Assert.assertNotNull(league);
		Assert.assertEquals(leagueName, league.getLeagueName());
	}
	
	@Test
	public void testGetStandingByTeamName()  {
		Map<String, String> standingsDetails = null;
		String leagueId = "149";
		String teamName = "West Brom";
		try {
			standingsDetails = teamStandingService.getTeamStandingsByTeamName(leagueId, teamName);
		} catch (Exception e) {
			Assert.fail("Exception thrown: " + e.getMessage());
		}
		Assert.assertNotNull(standingsDetails);
		Assert.assertEquals(teamName, standingsDetails.get("team_name"));
	}	
		

}
