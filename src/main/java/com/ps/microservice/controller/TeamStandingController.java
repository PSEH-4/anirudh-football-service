package com.ps.microservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ps.microservice.exception.InvalidRequestException;
import com.ps.microservice.model.StandingRequest;
import com.ps.microservice.model.StandingResponse;
import com.ps.microservice.service.TeamStandingService;

@RestController

public class TeamStandingController {
	
	@Autowired
	private TeamStandingService teamStandingService;
	
	@RequestMapping(value = "/football/standings", method = RequestMethod.GET)
	public ResponseEntity<StandingResponse> getTeamStandings(@RequestParam(value = "countryName",required = true) String countryName , @RequestParam(value = "leagueName",required = true) String leagueName, @RequestParam(value = "teamName",required = true) String teamName)
	{
		if (countryName.isEmpty() || leagueName.isEmpty() || teamName.isEmpty()) {
			Map<String, String> params = new HashMap<>();
			params.put("countryName", countryName);
			params.put("leagueName", leagueName);
			params.put("teamName", teamName);
			InvalidRequestException.createWith(params);
		}
		StandingRequest request = new StandingRequest(countryName, leagueName, teamName);
		StandingResponse response  = teamStandingService.getTeamStanding(request);
		
		return new ResponseEntity<StandingResponse>(response,HttpStatus.OK);
		}	
	
		

}
