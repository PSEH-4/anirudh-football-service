package com.ps.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ps.microservice.model.StandingRequest;
import com.ps.microservice.model.StandingResponse;
import com.ps.microservice.service.TeamStandingService;

@RestController
public class TeamStandingController {
	
	@Autowired
	private TeamStandingService teamStandingService;
	
	@RequestMapping(value = "/football/standings/{countryName}/{leagueName}/{teamName}", method = RequestMethod.GET)
	public ResponseEntity<StandingResponse> getTeamStandings(@PathVariable(value = "countryName",required = true) String countryName , @PathVariable(value = "leagueName",required = true) String leagueName, @PathVariable(value = "teamName",required = true) String teamName)
	{
		StandingRequest request = new StandingRequest(countryName, leagueName, teamName);
		StandingResponse response  = teamStandingService.getTeamStanding(request);
		
		return new ResponseEntity<StandingResponse>(response,HttpStatus.OK);
		}	
	
		

}
