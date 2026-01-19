<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="includes/header.jsp" />

<main>
  <div class="container">
    <h2>Editar usuário</h2>

    <div id="container-cad-usuario">
      <form action="atualizarUsuario" method="post" class="form-usuario">
        <label for="idusuario">Id:</label>
        <input
          type="text"
          name="idusuario"
          id="idusuario"
          value="${usuario.idusuario}"
          readonly
        />

        <label for="nome">Nome:</label>
        <input
          type="text"
          name="nome"
          id="nome"
          value="${usuario.nome}"
        />
        <span id="erro-nome" class="msg-erro"></span>

        <label for="email">E-mail:</label>
        <input
          type="email"
          name="email"
          id="email"
          value="${usuario.email}"
        />
        <span id="erro-email" class="msg-erro">${erroEmail}</span>

        <label for="senha">Senha:</label>
        <input
          type="password"
          name="senha"
          id="senha"
          placeholder="Nova senha..."
        />
        <span id="erro-senha" class="msg-erro"></span>

        <label for="data_criacao">Data criação:</label>
        <input
          type="text"
          name="data_criacao"
          id="data_criacao"
          value="${usuario.dataCriacao}"
          readonly
        />

        <label for="data_atualizacao">Data última atualização:</label>
        <input
          type="text"
          name="data_atualizacao"
          id="data_atualizacao"
          value="${usuario.dataAtualizacao}"
          readonly
        />

        <label for="status">Status:</label>
        <input
          type="text"
          name="status"
          id="status"
          value="${usuario.status.descricao}"
          readonly
        />

        <input type="submit" value="Salvar" class="btn-salvar-usuario" />
      </form>

      <a
        href="${pageContext.request.contextPath}/listarUsuarios"
        class="btn-cancelar"
        >Cancelar</a
      >
    </div>
  </div>
</main>

<script src="js/validar-form.js"></script>

<jsp:include page="includes/footer.jsp" />
