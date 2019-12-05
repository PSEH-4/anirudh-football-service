package com.ps.microservice.exception;

import java.util.Map;

public class InvalidRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9212051631608925716L;
	private Map<String, String> mandatoryParams;

    public static InvalidRequestException createWith(Map<String, String> mandatoryParams) {
        return new InvalidRequestException(mandatoryParams);
    }

    private InvalidRequestException(Map<String, String> mandatoryParams) {
        this.mandatoryParams = mandatoryParams;
    }

    @Override
    public String getMessage() {
    	return "Invalid Search Criteria specified. One or more mandatory params missing: " + mandatoryParams;

    }
}
