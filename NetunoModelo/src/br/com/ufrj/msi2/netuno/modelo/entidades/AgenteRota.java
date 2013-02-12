package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="agenteRota")
@PrimaryKeyJoinColumn(name="id")
public class AgenteRota extends Usuario implements Serializable {
	private static final long serialVersionUID = 3949146978649270591L;

	private Integer id;
	
	@OneToMany
	@JoinColumn(name="agenteRota_id")
	private List<Requisicao> requisicoes;

	public Integer getId() {
		return id;
	}

	public List<Requisicao> getRequisicoes() {
		return requisicoes;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRequisicoes(List<Requisicao> requisicoes) {
		this.requisicoes = requisicoes;
	}
}