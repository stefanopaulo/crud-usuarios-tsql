let formularioParaEnviar = null;

function confirmarExclusao() {
  const modal = document.getElementById("modalConfirmacao");
  const btnCancelar = document.getElementById("btnCancelar");
  const btnConfirmar = document.getElementById("btnConfirmar");

  const formsExcluir = document.querySelectorAll(".form-delete");

  formsExcluir.forEach((form) => {
    form.onsubmit = function (event) {
      event.preventDefault();
      formularioParaEnviar = form;
      modal.style.display = "block";
    };
  });

  btnCancelar.onclick = function () {
    modal.style.display = "none";
    formularioParaEnviar = null;
  };

  btnConfirmar.onclick = function () {
    if (formularioParaEnviar) {
      btnConfirmar.disabled = true;
      btnConfirmar.innerText = "Excluindo...";
      formularioParaEnviar.submit();
    }
  };
}

document.addEventListener("DOMContentLoaded", confirmarExclusao);
