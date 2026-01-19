package br.com.crud.exceptions;

public class PropertiesException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PropertiesException(String msg) {
		super(msg);
	}
	
	public PropertiesException(String msg, Exception e) {
		super(msg, e);
	}

}
