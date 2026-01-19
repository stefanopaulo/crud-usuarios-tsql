package br.com.crud.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.crud.exceptions.DBException;
import br.com.crud.model.dao.UsuarioDAO;
import br.com.crud.model.entities.Usuario;
import br.com.crud.model.enums.StatusUsuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection conn;

	UsuarioDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Usuario> buscarTodos() {
		String sql = "{ call sp_lista_usuarios_ativos }";

		List<Usuario> listusuarios = new ArrayList<Usuario>();

		try (CallableStatement cs = conn.prepareCall(sql); ResultSet rs = cs.executeQuery()) {

			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdusuario(rs.getInt("idusuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDataCriacao(rs.getString("data_criacao_ptbr"));
				usuario.setDataAtualizacao(rs.getString("data_atualizacao_ptbr"));

				listusuarios.add(usuario);
			}

		} catch (SQLException e) {
			throw new DBException("Erro ao buscar usuários. Causa: " + e.getMessage());
		}

		return listusuarios;
	}

	@Override
	public Usuario buscarPorId(int idusuario) {
		String sql = "{ call sp_buscar_usuario_por_id(?) }";

		Usuario usuario = null;

		try (CallableStatement cs = conn.prepareCall(sql)) {

			cs.setInt(1, idusuario);

			try (ResultSet rs = cs.executeQuery()) {
				if (rs.next()) {
					usuario = new Usuario(); // Só instancia se houver retorno
					usuario.setIdusuario(rs.getInt("idusuario"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setStatus(StatusUsuario.valueOf(rs.getString("status")));
					usuario.setDataCriacao(rs.getString("data_criacao_ptbr"));
					usuario.setDataAtualizacao(rs.getString("data_atualizacao_ptbr"));
				}
			}
		} catch (SQLException e) {
			throw new DBException("Erro ao buscar usuário. Causa: " + e.getMessage());
		}

		return usuario;
	}

	@Override
	public void inserir(Usuario usuario) {
		String sql = "{ call sp_inserir_usuario(?, ?, ?) }";

		try (CallableStatement cs = conn.prepareCall(sql)) {

			cs.setString(1, usuario.getNome());
			cs.setString(2, usuario.getEmail());
			cs.setString(3, usuario.getSenha());

			try (ResultSet rs = cs.executeQuery()) {
				if (rs.next()) {
					int idGerado = rs.getInt("novoId");
					usuario.setIdusuario(idGerado);
				}
			}

		} catch (SQLException e) {
			throw new DBException("Erro ao inserir usuário. Causa: " + e.getMessage());
		}
	}

	@Override
	public void atualizar(Usuario usuario) {
		String sql = "{ call sp_atualizar_usuario(?, ?, ?, ?) }";

		try (CallableStatement cs = conn.prepareCall(sql)) {

			cs.setInt(1, usuario.getIdusuario());
			cs.setString(2, usuario.getNome());
			cs.setString(3, usuario.getEmail());
			cs.setString(4, usuario.getSenha());

			cs.execute();
		} catch (SQLException e) {
			throw new DBException("Erro ao atualizar usuário. Causa: " + e.getMessage());
		}
	}

	@Override
	public void deletar(int idusuario) {
		String sql = "{ call sp_desativar_usuario(?) }";

		try (CallableStatement cs = conn.prepareCall(sql)) {

			cs.setInt(1, idusuario);

			cs.execute();
		} catch (SQLException e) {
			throw new DBException("Erro ao deletar usuário. Causa: " + e.getMessage());
		}
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		String sql = "{ call sp_validar_login(?, ?) }";
		
		Usuario usuario = null;
		
		try (CallableStatement cs = conn.prepareCall(sql)) {

			cs.setString(1, email);
			cs.setString(2, senha);
			
			try (ResultSet rs = cs.executeQuery()) {
				
				if (rs.next()) {
					usuario = new Usuario();
					usuario.setIdusuario(rs.getInt("idusuario"));
					usuario.setNome(rs.getString("nome"));
					usuario.setEmail(rs.getString("email"));
					usuario.setStatus(StatusUsuario.valueOf(rs.getString("status")));
					usuario.setEhAdmin(rs.getBoolean("eh_admin"));
				}
			}
			
		} catch (SQLException e) {
			throw new DBException("Erro ao deletar usuário. Causa: " + e.getMessage());
		}
		
		return usuario;
	}

}
