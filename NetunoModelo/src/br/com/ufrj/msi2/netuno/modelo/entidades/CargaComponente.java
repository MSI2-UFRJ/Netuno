package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Unidade que é alocada ou retirada de conteiners.
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="carga_componente")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class CargaComponente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(targetEntity = AgenteCarga.class)
	//@JoinColumn(name="agenteEmbarque_id")
	private AgenteCarga agenteEmbarque;
	
	@ManyToOne(targetEntity = AgenteCarga.class)
	//@JoinColumn(name="agenteDesembarque_id")
	private AgenteCarga agenteDesembarque;
	
	@ManyToOne(targetEntity = Conteiner.class)
	//@JoinColumn(name="conteiner_id")
	private Conteiner conteiner;

	@ManyToOne(targetEntity = Patio.class)
	private Patio patio;
	
	@Column(name = "peso")
	private Double peso;
	
	@Column(name="desembarcada")
	private boolean desembarcada;


	public Integer getId() {
		return id;
	}

	public AgenteCarga getAgenteEmbarque() {
		return agenteEmbarque;
	}

	public AgenteCarga getAgenteDesembarque() {
		return agenteDesembarque;
	}

	public Conteiner getConteiner() {
		return conteiner;
	}

	public Double getPeso() {
		return peso;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAgenteEmbarque(AgenteCarga agenteEmbarque) {
		this.agenteEmbarque = agenteEmbarque;
	}

	public void setAgenteDesembarque(AgenteCarga agenteDesembarque) {
		this.agenteDesembarque = agenteDesembarque;
	}

	public void setConteiner(Conteiner conteiner) {
		this.conteiner = conteiner;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Patio getPatio() {
		return patio;
	}

	public void setPatio(Patio patio) {
		this.patio = patio;
	}
	
	public boolean isDesembarcada() {
		return desembarcada;
	}

	public void setDesembarcada(boolean desembarcada) {
		this.desembarcada = desembarcada;
	}


}
