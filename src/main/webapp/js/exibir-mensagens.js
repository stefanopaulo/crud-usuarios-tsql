function verificarMensagem() {
    const urlParams = new URLSearchParams(window.location.search);
    const toast = document.getElementById("toast");

    if (urlParams.has('msg')) {
        const tipoMsg = urlParams.get('msg');
        const categoria = urlParams.get('tipo') || 'sucesso';

        const mensagens = {
            "usuarioSalvo": "Usuário salvo com sucesso!",
            "usuarioEditado": "Alterações salvas com sucesso!",
            "acessoNegado": "Acesso negado: Você não tem permissão!"
        };

        // 1. Define o texto e limpa classes anteriores
        toast.textContent = mensagens[tipoMsg] || "Operação realizada!";
        toast.className = ""; // Reseta todas as classes para reiniciar a animação

        // 2. Força um "reflow" (truque para o navegador perceber a mudança de classe)
        void toast.offsetWidth;

        // 3. Aplica as classes novas
        toast.classList.add(categoria);
        toast.classList.add("show");

        // 4. Limpa a URL após 500ms (para garantir que o JS leu tudo com calma)
        setTimeout(() => {
            window.history.replaceState({}, document.title, window.location.pathname);
        }, 500);

        // 5. Esconde o toast
        setTimeout(() => {
            toast.classList.remove("show");
        }, 3500);
    }
}

document.addEventListener("DOMContentLoaded", verificarMensagem);