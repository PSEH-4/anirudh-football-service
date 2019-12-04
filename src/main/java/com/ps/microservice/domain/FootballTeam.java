/**
 * 
 */
package com.ps.microservice.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author sapient_a
 *
 */

@Slf4j
@Data
public class FootballTeam {
	
	private String teamId;
	private String teamName;
	private League league;
	private Country country;

	

}
