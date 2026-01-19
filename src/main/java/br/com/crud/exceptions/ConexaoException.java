package br.com.crud.exceptions;

public class ConexaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ConexaoException(String msg) {
		super(msg);
	}
	
	public ConexaoException(String msg, Exception e) {
		super(msg, e);
	}

}
