package com.ps.microservice.model;

import lombok.Data;

@Data
public class StandingRequest {
	
	private String countryName;
	private String leagueName;
	private String teamName;
	
	public StandingRequest(String countryName, String leagueName, String teamName) {
		this.countryName = countryName;
		this.leagueName = leagueName;
		this.teamName = teamName;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public String getTeamName() {
		return teamName;
	}

}
