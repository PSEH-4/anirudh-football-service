package com.ps.microservice.exception;

import java.util.Map;

public class InvalidRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9212051631608925716L;
	private Map<String, String> searchParams;

    public static InvalidRequestException createWith(Map<String, String> searchParams) {
        return new InvalidRequestException(searchParams);
    }

    private InvalidRequestException(Map<String, String> searchParams) {
        this.searchParams = searchParams;
    }

    @Override
    public String getMessage() {
        return "Team with Search Params '" + searchParams + "' not found";
    }
}
