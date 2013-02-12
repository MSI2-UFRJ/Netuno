package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="requisicao")
public class Requisicao implements Serializable {
	private static final long serialVersionUID = 381025165479150169L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    private Navio navio;
	
	@ManyToOne
    private Conteiner conteiner;
	
	@ManyToOne
    private AgenteCarga agenteCarga;
	
	@Column(name = "data")
	private Date data;

	public Integer getId() {
		return id;
	}

	public Navio getNavio() {
		return navio;
	}

	public Conteiner getConteiner() {
		return conteiner;
	}

	public AgenteCarga getAgenteCarga() {
		return agenteCarga;
	}

	public Date getData() {
		return data;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public void setConteiner(Conteiner conteiner) {
		this.conteiner = conteiner;
	}

	public void setAgenteCarga(AgenteCarga agenteCarga) {
		this.agenteCarga = agenteCarga;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
