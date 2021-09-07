package com.livrariasaara.domain.exception;

public class ObjectNoContentException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public ObjectNoContentException(String msg) {
	        super(msg);
	    }

	    public ObjectNoContentException(String msg, Throwable cause) {
	        super(msg, cause);
	    }

}
