package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="agenteRota")
public class AgenteRota extends Usuario {
	private static final long serialVersionUID = 3949146978649270591L;
	
	@OneToMany
	@JoinColumn(name="agenteRota_id")
	private List<Requisicao> requisicoes;

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}
	
	public String getTipo() {
		return "agenteRota";
	}
	
}