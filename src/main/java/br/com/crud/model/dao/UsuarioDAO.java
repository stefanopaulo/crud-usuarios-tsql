package br.com.crud.model.dao;

import java.util.List;

import br.com.crud.model.entities.Usuario;

public interface UsuarioDAO {
	List<Usuario> buscarTodos();
	Usuario buscarPorId(int idusuario);
	void inserir(Usuario usuario);
	void atualizar(Usuario usuario);
	void deletar(int idusuario);
}
