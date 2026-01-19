<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />

<main>
  <div class="container">
    <h2>Cadastrar novo usuário</h2>

    <div id="container-cad-usuario">
      <form action="cadastrarUsuario" method="post" class="form-usuario">
        <label for="nome">Nome:</label>
        <input
          type="text"
          name="nome"
          id="nome"
          value="${usuario.nome}"
          placeholder="Nome..."
        />
        <span id="erro-nome" class="msg-erro"></span>

        <label for="email">E-mail:</label>
        <input
          type="email"
          name="email"
          id="email"
          value="${usuario.email}"
          placeholder="E-mail..."
        />
        <span id="erro-email" class="msg-erro">${erroEmail}</span>

        <label for="senha">Senha:</label>
        <input
          type="password"
          name="senha"
          id="senha"
          placeholder="Senha..."
        />
        <span id="erro-senha" class="msg-erro"></span>

        <input type="submit" value="Salvar" class="btn-salvar-usuario" />
      </form>
    </div>
  </div>
</main>

<script src="js/validar-form.js"></script>

<jsp:include page="includes/footer.jsp" />
