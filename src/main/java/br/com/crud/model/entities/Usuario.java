package br.com.crud.model.entities;

import java.util.Objects;

import br.com.crud.model.enums.StatusUsuario;

public class Usuario {
	private Integer idusuario;
	private String nome;
	private String email;
	private String senha;
	private StatusUsuario status;
	private Boolean ehAdmin;
	private String dataCriacao;
	private String dataAtualizacao;

	public Usuario() {
	}

	public Usuario(Integer idusuario, String nome, String email, String senha, StatusUsuario status, Boolean ehAdmin,
			String dataCriacao, String dataAtualizacao) {
		super();
		this.idusuario = idusuario;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.status = status;
		this.ehAdmin = ehAdmin;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public StatusUsuario getStatus() {
		return status;
	}

	public void setStatus(StatusUsuario status) {
		this.status = status;
	}

	public Boolean getEhAdmin() {
		return ehAdmin;
	}

	public void setEhAdmin(Boolean ehAdmin) {
		this.ehAdmin = ehAdmin;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idusuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idusuario, other.idusuario);
	}

	@Override
	public String toString() {
		return "Usuario [idusuario=" + idusuario + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", status=" + status + ", ehAdmin=" + ehAdmin + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + "]";
	}
}
