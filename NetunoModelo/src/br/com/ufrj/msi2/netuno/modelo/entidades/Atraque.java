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
@Table(name="atraque")
public class Atraque implements Serializable{

	private static final long serialVersionUID = -3104812463055680435L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Navio navio;
	
	@ManyToOne
	private Porto porto;
	
	@ManyToOne
	//Não sei se isso é ideal. O que desejo é algo como ManyToOneOrZero.
	private Slot slot;
	
	public Slot getSlot() {
		return slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@Column(name="data_previsao_chegada")
	private Date data_previsao_chegada;

	@Column(name="data_previsao_saida")
	private Date data_previsao_saida;
	
	public Date getData_previsao_chegada() {
		return data_previsao_chegada;
	}

	public void setData_previsao_chegada(Date data_previsao_chegada) {
		this.data_previsao_chegada = data_previsao_chegada;
	}

	public Date getData_previsao_saida() {
		return data_previsao_saida;
	}

	public void setData_previsao_saida(Date data_previsao_saida) {
		this.data_previsao_saida = data_previsao_saida;
	}

	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}
}
