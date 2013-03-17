package br.com.ufrj.msi2.netuno.modelo.entidades;

/**
 * É feito por um AgenteRota que quer quer indicar em qual navio um conteiner deve ser embarcado.
 * @author Luiz, Bruno
 *
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQueries(
	{
		@NamedQuery(name="Requisicao.obterPorId",
			query="select r from Requisicao r where r.id = :id"),
		@NamedQuery(name="Requisicao.obterTodos",
			query="select r from Requisicao r"),
		@NamedQuery(name="Requisicao.obterPorAgenteCarga",
			query="select r from Requisicao r where r.agenteCarga = :agente and r.requisicaoAtendida = false")
	}
)
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

	@Column(name = "requisicaoAtendida")
	private boolean requisicaoAtendida = false;

	public boolean isRequisicaoAtendida() {
		return requisicaoAtendida;
	}

	public void setRequisicaoAtendida(boolean requisicaoAtendida) {
		this.requisicaoAtendida = requisicaoAtendida;
	}

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
