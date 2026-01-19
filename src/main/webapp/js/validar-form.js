let formularioParaEnviar = null;

function configurarValidacaoFormulario() {
  const form = document.querySelector(".form-usuario");

  if (form) {
    form.onsubmit = function (event) {
      if (!validarCampos(form)) {
        event.preventDefault();
      }
    };
  }
}

function validarCampos(form) {
  let formularioValido = true;

  const inputNome = form.querySelector("#nome");
  const inputEmail = form.querySelector("#email");
  const inputSenha = form.querySelector("#senha");

  const erroNome = form.querySelector("#erro-nome");
  const erroEmail = form.querySelector("#erro-email");
  const erroSenha = form.querySelector("#erro-senha");

  // Limpar estados de erro anteriores
  const spans = form.querySelectorAll(".msg-erro");
  spans.forEach((span) => (span.textContent = ""));

  const inputs = form.querySelectorAll("input");
  inputs.forEach((input) => input.classList.remove("campo-erro"));

  if (inputNome && inputNome.value.trim().length === 0) {
    erroNome.textContent = "Informe o nome do usuário *";
    inputNome.classList.add("campo-erro");
    formularioValido = false;
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(inputEmail.value.trim())) {
    erroEmail.textContent = "Informe um e-mail válido *";
    inputEmail.classList.add("campo-erro");
    formularioValido = false;
  }

  if (inputSenha.value.trim().length === 0) {
    erroSenha.textContent = "Informe a senha do usuário *";
    inputSenha.classList.add("campo-erro");
    formularioValido = false;
  }

  return formularioValido;
}

document.addEventListener("DOMContentLoaded", configurarValidacaoFormulario);

const campos = document.querySelectorAll("input");
campos.forEach((campo) => {
  campo.addEventListener("input", () => {
    campo.classList.remove("campo-erro");
    const spanErro = document.getElementById(`erro-${campo.id}`);
    if (spanErro) spanErro.textContent = "";
  });
});
