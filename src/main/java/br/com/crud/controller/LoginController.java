package br.com.crud.controller;

import java.io.IOException;
import java.sql.Connection;

import br.com.crud.model.dao.UsuarioDAO;
import br.com.crud.model.dao.impl.DaoFactory;
import br.com.crud.model.entities.Usuario;
import br.com.crud.util.Conexao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/autenticar", "/logout" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.getSession().invalidate();
        
        response.sendRedirect("index.jsp");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        if (email == null || email.isBlank() || senha == null || senha.isBlank()) {
            request.setAttribute("erroLogin", "Campos obrigatórios não preenchidos.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        try (Connection conn = Conexao.getConexao()) {
            UsuarioDAO dao = DaoFactory.createUsuarioDAO(conn);
            
            Usuario usuario = dao.autenticar(email, senha);
            
            if (usuario != null) {
                request.getSession().setAttribute("usuarioLogado", usuario);
                response.sendRedirect("listarUsuarios");
            } else {
                request.setAttribute("erroLogin", "E-mail ou senha incorretos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("erroLogin", "Erro técnico: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}
