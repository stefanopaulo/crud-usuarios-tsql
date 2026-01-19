package br.com.crud.model.dao.impl;

import java.sql.Connection;

import br.com.crud.model.dao.UsuarioDAO;

public class DaoFactory {
	private DaoFactory() {
	}
	
	public static UsuarioDAO createUsuarioDAO(Connection conn) {
		return new UsuarioDAOImpl(conn);
	}
}
