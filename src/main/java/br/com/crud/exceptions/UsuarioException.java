package br.com.crud.exceptions;

public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UsuarioException(String msg) {
		super(msg);
	}
	
	public UsuarioException(String msg, Exception e) {
		super(msg, e);
	}
}
