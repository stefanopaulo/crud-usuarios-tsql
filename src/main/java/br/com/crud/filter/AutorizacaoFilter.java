package br.com.crud.filter;

import java.io.IOException;

import br.com.crud.model.entities.Usuario;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class AutorizacaoFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // 1. Pega a URL que o usuário está tentando acessar
        String path = httpRequest.getContextPath();
        String uri = httpRequest.getRequestURI();

        // 2. Define quais URLs são PÚBLICAS (não precisam de login)
        boolean ehPaginaLogin = uri.endsWith("index.jsp") || uri.endsWith("autenticar") || uri.equals(path + "/");
        boolean ehRecursoEstatico = uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/");

        // 3. Verifica se o usuário está logado
        boolean logado = (session != null && session.getAttribute("usuarioLogado") != null);

        if (logado || ehPaginaLogin || ehRecursoEstatico) {
        	if (uri.contains("novoUsuario") ||       // GET: Abre o form de cadastro
        		    uri.contains("cadastrarUsuario") ||  // POST: Salva o novo usuário
        		    uri.contains("editarUsuario") ||     // GET: Abre o form de edição
        		    uri.contains("atualizarUsuario") ||  // POST: Salva a alteração
        		    uri.contains("deletarUsuario")) {    // POST: Remove do banco

        		    Usuario u = (Usuario) session.getAttribute("usuarioLogado");
        		    
        		    if (u == null || !u.getEhAdmin()) {
        		        // Se não for admin, volta para a lista com aviso
        		        httpResponse.sendRedirect(path + "/listarUsuarios?msg=acessoNegado&tipo=erro");
        		        return; 
        		    }
        		}
        	
            chain.doFilter(request, response);
        } else {
            // Se não, redireciona para o login com uma mensagem de erro
            httpRequest.setAttribute("erroLogin", "Acesso negado. Por favor, faça login.");
            httpRequest.getRequestDispatcher("index.jsp").forward(httpRequest, httpResponse);
        }
		
	}

}
