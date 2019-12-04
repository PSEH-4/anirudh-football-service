package com.ps.microservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ps.microservice.domain.Country;
import com.ps.microservice.domain.League;
import com.ps.microservice.exception.NoDataFoundException;
import com.ps.microservice.model.StandingRequest;
import com.ps.microservice.model.StandingResponse;

@Service
public class TeamStandingService {


	private Logger logger = LoggerFactory.getLogger(getClass());
	
//	@Autowired
//	private EurekaClient discoveryClient;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Value("${footballApi.url}")
	private String apiUrl;
	
	@Value("${footballApi.key}")
	private String apiKey;	
	
/*	@HystrixCommand(fallbackMethod="imageServiceNotAvailable")
	public Map<String, String> getRandomImage(){
		InstanceInfo ii = discoveryClient.getNextServerFromEureka("IMAGES-MICROSERVICE", false);
		String homePageUrl = ii.getHomePageUrl();
		Map<String, String> imageInfo = restTemplate.exchange(homePageUrl+"/images?random=true&fields=url", HttpMethod.GET, null, new ParameterizedTypeReference<Map<String,String>>() {}, new Object[]{}).getBody();
		return imageInfo;
	}*/
	
	public StandingResponse getTeamStanding(StandingRequest request){
		
		return null;
	}
	
	protected Map<String, String> imageServiceNotAvailable(){
		logger.warn("imageServiceNotAvailable()");
		Map<String, String> i = new HashMap<String, String>();
		i.put("imageUrl", "https://camo.githubusercontent.com/e871b5d002a9699e7a2d9fa0178af5c72f0743e0/68747470733a2f2f6e6574666c69782e6769746875622e636f6d2f487973747269782f696d616765732f687973747269782d6c6f676f2d7461676c696e652d3835302e706e67");
		return i;
	}
	
	public Country getCountry(StandingRequest request) {
		String requestUrl = apiUrl + "?action={apiAction}&APIkey={apiKey}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("apiAction", "get_countries");
		uriVariables.put("apiKey", apiKey);
		
	    List<Country> countryList = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {}, uriVariables).getBody();
	    List<Country> matchedCountry = countryList.stream()
	    .filter(country -> country.getCountryName().equals(request.getCountryName()))
	    .collect(Collectors.toList());
	    
	    if (matchedCountry.isEmpty() ) {
	    	throw NoDataFoundException.createWith("Given Country name not found");
	    }
	    
	    if (matchedCountry.size() > 1 ) {
	    	throw new RuntimeException("Inconsistent Data");
	    }
	    
	    return matchedCountry.get(0);
	    
	}
	
	public League getLeague(String countryId, StandingRequest request) {
		String requestUrl = apiUrl + "?action={apiAction}&APIkey={apiKey}&country_id={countryId}";
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("apiAction", "get_leagues");
		uriVariables.put("apiKey", apiKey);
		uriVariables.put("country_id=", apiKey);
		
	    List<League> leagueList = restTemplate.exchange(requestUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<League>>() {}, uriVariables).getBody();
	    List<League> matchedLeague = leagueList.stream()
	    .filter(league -> league.getLeagueName().equals(request.getLeagueName()))
	    .collect(Collectors.toList());
	    
	    if (matchedLeague.isEmpty() ) {
	    	throw NoDataFoundException.createWith("Given League name not found");
	    }
	    
	    if (matchedLeague.size() > 1 ) {
	    	throw new RuntimeException("Inconsistent Data");
	    }
	    
	    return matchedLeague.get(0);
	    
	}	
}
