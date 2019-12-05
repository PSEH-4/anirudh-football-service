package com.ps.microservice.model;

import lombok.Data;

@Data
public class StandingResponse {
	
	private String countryInfo;
	private String leagueInfo;
	private String teamInfo;
	private int overallPosition;
	public String getCountryInfo() {
		return countryInfo;
	}
	public void setCountryInfo(String countryInfo) {
		this.countryInfo = countryInfo;
	}
	public String getLeagueInfo() {
		return leagueInfo;
	}
	public void setLeagueInfo(String leagueInfo) {
		this.leagueInfo = leagueInfo;
	}
	public String getTeamInfo() {
		return teamInfo;
	}
	public void setTeamInfo(String teamInfo) {
		this.teamInfo = teamInfo;
	}
	public int getOverallPosition() {
		return overallPosition;
	}
	public void setOverallPosition(int overallPosition) {
		this.overallPosition = overallPosition;
	}

}
