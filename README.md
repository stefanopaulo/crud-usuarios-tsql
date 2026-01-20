# 👥 Sistema de Gerenciamento de Usuários (CRUD)

Este projeto é uma aplicação **Java Web** desenvolvida com foco em **arquitetura orientada a banco de dados**, onde as regras de negócio são centralizadas em **Stored Procedures e Triggers**, e a camada Java atua exclusivamente como meio de comunicação entre o front-end e o banco.

O objetivo deste repositório é demonstrar um modelo de aplicação **comum em sistemas corporativos e legados**, especialmente em ambientes que utilizam **Oracle ou SQL Server**, onde grande parte da lógica reside no banco de dados.

---

## 🎯 Objetivo do Projeto

Este projeto foi construído como **exercício prático e peça de portfólio**, com os seguintes objetivos principais:

* Consolidar o uso de **JDBC puro** para comunicação com banco de dados.
* Aplicar regras de negócio diretamente no **banco (procedures, triggers e views)**.
* Manter o **Java simples e enxuto**, sem sobrecarga de frameworks ou camadas desnecessárias.
* Trabalhar um CRUD completo com **boas práticas de organização**, mesmo em um projeto pequeno.

**Decisões arquiteturais importantes:**

* O Java **não executa regras de negócio**.
* Datas de criação e atualização são gerenciadas **exclusivamente pelo banco**.
* Views no banco são utilizadas apenas para **exibição de dados formatados**.

---

## 🛠️ Tecnologias Utilizadas

* **Linguagens:** Java & T-SQL
* **Servidor de Aplicação:** Apache Tomcat
* **View:** JSP & JSTL
* **Banco de Dados:** SQL Server
* **Persistência:** JDBC
* **Frontend:** HTML5, CSS3 e JavaScript (Vanilla)
* **IDE:** Eclipse (Dynamic Web Project)

---

## 🚀 Funcionalidades

* [x] **Cadastro de Usuários:** Inclusão de novos usuários com validações de negócio centralizadas no banco de dados.
* [x] **Listagem de Usuários Ativos:** Consulta utilizando View para exibição de dados formatados.
* [x] **Edição de Usuários:** Atualização de dados com controle de integridade (e-mail único).
* [x] **Desativação de Usuários (Soft Delete):** Exclusão lógica via procedure, preservando histórico.
* [x] **Confirmação de Exclusão:** Modal customizado para evitar ações destrutivas acidentais.
* [x] **Segurança e Proteção de Rotas (Middleware):** Implementação de Filter para interceptar requisições, garantindo que usuários não autenticados não acessem páginas internas via URL.
* [x] **Controle de Autorização (RBAC):** Restrição de funcionalidades críticas (Cadastro, Edição, Exclusão) exclusivamente para usuários com perfil de Administrador.
* [x] **Criptografia:** Autenticação protegida por Hash SHA-256 executado diretamente no banco de dados via Procedure.
* [x] **Experiência do Usuário (UX):** Notificações dinâmicas (Toast) com cores distintas para sucesso e erro, além de modal customizado para confirmação de exclusão.

---

## 📸 Demonstração da Interface

[screen-capture.webm](https://github.com/user-attachments/assets/1ceb383e-6c1e-4144-8c82-1d2a85d48226)

---

## 🧠 Arquitetura do Projeto

* **Banco de Dados**

  * Regras de negócio implementadas com **Stored Procedures e Triggers**.
  * Scripts organizados na pasta `/sql`.
  * Controle automático de datas de criação e atualização.
  * Segurança de Dados: Uso de HASHBYTES para proteção de credenciais, garantindo que senhas nunca sejam armazenadas em texto pleno.

* **Camada Java**

  * Comunicação direta com o banco via JDBC.
  * DAOs responsáveis apenas por executar procedures.
  * Sem camada de Service ou DTO, por decisão consciente de escopo.

* **Front-end**

  * Formulários simples e funcionais.
  * Validações em JavaScript puro.
  * Modal customizado para confirmação de exclusão.

---

## 🔮 Evoluções Finalizadas

Este projeto **foi** evoluído incrementalmente, sem alterar sua proposta inicial:

* [x] Implementação de **DAO Factory** para abstrair criação de DAOs e injeção de conexão.
* [x] Tela de **Login** com controle de sessão.
* [x] Autorização baseada no campo `eh_admin`.
* [x] Melhoria na organização de pacotes.

Essas evoluções representam **crescimento do sistema**, não correção de falhas.

---

## ⚙️ Como executar o projeto

Este é um **Dynamic Web Project** desenvolvido no Eclipse.

1. **Clone o repositório:**

   ```bash
   git clone https://github.com/stefanopaulo/crud-usuarios-tsql.git
   ```

2. **Importe no Eclipse:**

   * `File > Import > General > Existing Projects into Workspace`
   * Selecione a pasta do projeto.

3. **Banco de Dados:**

   * Execute os scripts disponíveis na pasta `/sql`.
   * Ajuste usuário, senha e URL de conexão conforme seu ambiente, na fábrica de conexões.

4. **Servidor:**

   * Execute o projeto em um servidor **Apache Tomcat**.
   * Acesse via navegador: `http://localhost:8080/crud-usuarios-tsql

---

## 👨‍💻 Autor

**Stefano Paulo**
*Desenvolvedor Java focado em fundamentos sólidos, JDBC e arquitetura de sistemas corporativos.*
