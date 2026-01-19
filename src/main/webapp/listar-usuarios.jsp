<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<jsp:include page="includes/header.jsp" />

<main>
  <div class="container">
    <h2>Listagem de usuarios</h2>

    <table id="container-table">
      <thead>
        <tr>
          <th>Nome</th>
          <th>E-mail</th>
          <th>Data de criação</th>
          <th>Data última atualização</th>
          <th>Ações</th>
        </tr>
      </thead>

      <tbody>
        <c:forEach var="u" items="${usuarios}">
          <tr>
            <td>${u.nome}</td>
            <td>${u.email}</td>
            <td>${u.dataCriacao}</td>
            <td>${u.dataAtualizacao}</td>

            <td>
              <div class="acoes">
                <a href="editarUsuario?idusuario=${u.idusuario}" class="btn-editar">Editar</a>

                <form action="deletarUsuario" method="post" class="form-delete">
                  <input type="hidden" name="idusuario" value="${u.idusuario}" />
                  <input type="submit" class="btn-excluir" value="Excluir" />
                </form>
              </div>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</main>

<div id="modalConfirmacao" class="modal">
  <div class="modal-content">
    <h3>Confirmação</h3>
    <p>Tem certeza que deseja excluir este usuário?</p>
    <div class="modal-buttons">
      <button type="button" id="btnCancelar" class="botao-cancelar">Cancelar</button>
      <button type="button" id="btnConfirmar" class="botao-confirmar">Excluir</button>
    </div>
  </div>
</div>

<div id="toast"></div>

<script src="js/confirmar-exclusao.js"></script>
<script src="js/exibir-mensagens.js"></script>

<jsp:include page="includes/footer.jsp" />
