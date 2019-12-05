package com.ps.microservice.exception;

import lombok.Getter;

public class NoDataFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5388164124636063092L;
	
	String errorMessage;

    public static NoDataFoundException createWith(String errorMessage) {
        return new NoDataFoundException(errorMessage);
    }

    private NoDataFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return "Team with given Search Params not found";
    }
}
