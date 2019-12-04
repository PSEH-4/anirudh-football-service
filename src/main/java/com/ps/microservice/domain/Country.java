/**
 * 
 */
package com.ps.microservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author sapient_a
 *
 */

@Data
public class Country {
	
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


}
