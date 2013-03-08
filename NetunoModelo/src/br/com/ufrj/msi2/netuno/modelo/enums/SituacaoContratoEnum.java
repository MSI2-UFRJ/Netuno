package br.com.ufrj.msi2.netuno.modelo.enums;

public enum SituacaoContratoEnum {
	
	finalizado("finalizado"), aberto("aberto");
	
	private String descricao;

	private SituacaoContratoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return this.descricao;
	}

}
