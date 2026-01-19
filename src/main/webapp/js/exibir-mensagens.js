function verificarMensagem() {
  const urlParams = new URLSearchParams(window.location.search);
  const toast = document.getElementById("toast");

  if (urlParams.has('msg')) {
    const tipoMsg = urlParams.get('msg');
    
    // Dicionário de mensagens
    const mensagens = {
      "usuarioSalvo": "Usuário salvo com sucesso!",
      "usuarioEditado": "Alterações salvas com sucesso!"
    };

    // Define o texto baseado no parâmetro da URL
    toast.textContent = mensagens[tipoMsg] || "Operação realizada!";
    
    // Mostra o toast
    toast.className = "show";

    // Limpa a URL para estética e evitar repetição no F5
    window.history.replaceState({}, document.title, window.location.pathname);

    setTimeout(() => { 
      toast.className = toast.className.replace("show", ""); 
    }, 3000);
  }
}

document.addEventListener("DOMContentLoaded", verificarMensagem);