<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp" />

<main>
  <div class="container">
    <h2>Bem-vindo</h2>

   <form action="autenticar" method="post" class="form-usuario">
    <input type="email" name="email" id="email" placeholder="E-mail..." />
    <span id="erro-email" class="msg-erro">${erroEmail}</span>

    <input type="password" name="senha" id="senha" placeholder="Senha..." />
    <span id="erro-senha" class="msg-erro"></span>

    <input type="submit" value="Entrar" class="btn-salvar-usuario" />

    <span id="erro-login" class="msg-erro">${erroLogin}</span>
   </form>
  </div>
</main>

<script src="js/validar-form.js"></script>

<jsp:include page="includes/footer.jsp" />
