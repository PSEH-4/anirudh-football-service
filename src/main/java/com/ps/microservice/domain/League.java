package com.ps.microservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class League {
	
	@JsonProperty(value = "league_id")
	private String leagueId;
	
	@JsonProperty(value = "league_name")
	private String leagueName;
	
	@JsonProperty(value = "country_id")
	private String countryId;
	
	@JsonProperty(value = "country_name")
	private String countryName;		
	
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(String leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	
	@Override
	public String toString() {
		return this.leagueId + " - " + this.leagueName;
	}	

}
