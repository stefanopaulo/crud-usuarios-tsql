<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <title>CRUD de Usuários</title>
    <!-- Fonte -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/css/styles.css"
    />
    <link
      rel="shortcut icon"
      href="${pageContext.request.contextPath}/images/favicon.png"
      type="image/x-icon"
    />
  </head>
  <body>
    <header id="cabecalho">
      <h1>CRUD de Usuários</h1>

      <nav id="nav-header">
        <c:if test="${not empty sessionScope.usuarioLogado}">
          <c:if test="${sessionScope.usuarioLogado.ehAdmin}">
            <a
              href="${pageContext.request.contextPath}/novoUsuario"
              id="link-cadastrar"
              >Cadastrar usuário</a
            >
          </c:if>

          <a href="${pageContext.request.contextPath}/logout" id="btn-sair"
            >Sair</a
          >
        </c:if>
      </nav>
    </header>