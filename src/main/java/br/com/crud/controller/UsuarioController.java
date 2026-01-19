package br.com.crud.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.crud.exceptions.ConexaoException;
import br.com.crud.exceptions.DBException;
import br.com.crud.exceptions.UsuarioException;
import br.com.crud.model.dao.UsuarioDAO;
import br.com.crud.model.dao.impl.DaoFactory;
import br.com.crud.model.entities.Usuario;
import br.com.crud.model.enums.StatusUsuario;
import br.com.crud.util.Conexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/controller", "/listarUsuarios", "/novoUsuario", "/cadastrarUsuario", "/editarUsuario",
		"/atualizarUsuario", "/deletarUsuario" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try (Connection conn = Conexao.getConexao()) {
			
			UsuarioDAO dao = DaoFactory.createUsuarioDAO(conn);
			
			switch (action) {
			case "/listarUsuarios":
				listarUsuarios(request, response, dao);
				break;
			case "/novoUsuario":
				request.getRequestDispatcher("novo-usuario.jsp").forward(request, response);
				break;
			case "/editarUsuario":
				editarUsuario(request, response, dao);
				break;
			default:
				response.sendRedirect("index.jsp");
			}
		} catch (ConexaoException e) {
			throw new UsuarioException(e.getMessage());
		} catch (SQLException e) {
			throw new UsuarioException("Erro interno ao finalizar operação no banco de dados.", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getServletPath();

		try (Connection conn = Conexao.getConexao()) {
			
			UsuarioDAO dao = DaoFactory.createUsuarioDAO(conn);
			
			switch (action) {
			case "/cadastrarUsuario":
				cadastrarUsuario(request, response, dao);
				break;
			case "/atualizarUsuario":
				atualizarUsuario(request, response, dao);
				break;
			case "/deletarUsuario":
				deletarUsuario(request, response, dao);
				break;
			default:
				response.sendRedirect("index.jsp");
			}
		} catch (ConexaoException e) {
			throw new UsuarioException(e.getMessage());
		} catch (SQLException e) {
			throw new UsuarioException("Erro interno ao finalizar operação no banco de dados.", e);
		}
	}

	protected void listarUsuarios(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
			throws ServletException, IOException {
		List<Usuario> usuarios = dao.buscarTodos();

		request.setAttribute("usuarios", usuarios);
		request.getRequestDispatcher("listar-usuarios.jsp").forward(request, response);
	}

	protected void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
			throws ServletException, IOException {
		Usuario usuario = new Usuario();

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		if (nome == null || nome.isBlank())
			throw new UsuarioException("Erro a buscar parametro nome");
		if (email == null || email.isBlank())
			throw new UsuarioException("Erro a buscar parametro email");
		if (senha == null || senha.isBlank())
			throw new UsuarioException("Erro a buscar parametro senha");

		try {
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);

			dao.inserir(usuario);

			response.sendRedirect("listarUsuarios?msg=usuarioSalvo");
		} catch (DBException e) {
			request.setAttribute("erroEmail", e.getMessage());
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("novo-usuario.jsp").forward(request, response);
		}
	}

	protected void editarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
			throws ServletException, IOException {
		String idParam = request.getParameter("idusuario");

		if (idParam == null || idParam.isBlank()) {
			throw new UsuarioException("Erro ao buscar parametro idusuario");
		}

		try {
			int idusuario = Integer.parseInt(idParam);

			Usuario usuario = dao.buscarPorId(idusuario);

			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("editar-usuario.jsp").forward(request, response);

		} catch (NumberFormatException e) {
			throw new UsuarioException("Erro ao converter id do usuário. Causa: " + e.getMessage());
		} catch (DBException e) {
			throw new UsuarioException("Erro ao carregar edição. Causa: " + e.getMessage());
		}
	}

	protected void atualizarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
			throws ServletException, IOException {
		Usuario usuario = new Usuario();

		String idParam = request.getParameter("idusuario");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		if (idParam == null || idParam.isBlank())
			throw new UsuarioException("Erro ao buscar parametro idusuario");
		if (nome == null || nome.isBlank())
			throw new UsuarioException("Erro a buscar parametro nome");
		if (email == null || email.isBlank())
			throw new UsuarioException("Erro a buscar parametro email");
		if (senha == null || senha.isBlank())
			throw new UsuarioException("Erro a buscar parametro senha");

		try {
			usuario.setIdusuario(Integer.parseInt(idParam));
			usuario.setNome(nome);
			usuario.setEmail(email);
			usuario.setSenha(senha);
			usuario.setDataCriacao(request.getParameter("data_criacao"));
			usuario.setDataAtualizacao(request.getParameter("data_atualizacao"));

			if ("Ativo".equals(request.getParameter("status"))) {
				usuario.setStatus(StatusUsuario.A);
			}

			dao.atualizar(usuario);

			response.sendRedirect("listarUsuarios?msg=usuarioEditado");
		} catch (NumberFormatException e) {
			throw new UsuarioException("Erro ao converter id do usuário. Causa: " + e.getMessage());
		} catch (DBException e) {
			request.setAttribute("erroEmail", e.getMessage());
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("editar-usuario.jsp").forward(request, response);
		}
	}

	protected void deletarUsuario(HttpServletRequest request, HttpServletResponse response, UsuarioDAO dao)
			throws ServletException, IOException {
		String idParam = request.getParameter("idusuario");

		if (idParam == null || idParam.isBlank()) {
			throw new UsuarioException("Erro ao buscar parametro idusuario");
		}

		try {
			int idusuario = Integer.parseInt(idParam);

			dao.deletar(idusuario);

			response.sendRedirect("listarUsuarios");
		} catch (NumberFormatException e) {
			throw new UsuarioException("Erro ao converter id do usuário. Causa: " + e.getMessage());
		} catch (DBException e) {
			throw new UsuarioException(e.getMessage());
		}
	}

}
