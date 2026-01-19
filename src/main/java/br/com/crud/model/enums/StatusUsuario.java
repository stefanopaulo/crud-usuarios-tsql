package br.com.crud.model.enums;

public enum StatusUsuario {
	A("Ativo"), I("Inativo");
	
	private String descricao;

	private StatusUsuario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
