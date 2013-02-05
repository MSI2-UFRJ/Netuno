package br.com.ufrj.msi2.netuno.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Carga implements Serializable {
	private static final long serialVersionUID = 3412494703857073751L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private Double peso;
	
	@Column(name="navio_id")
	private Integer idNavio;

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdNavio() {
		return idNavio;
	}

	public void setIdNavio(Integer idNavio) {
		this.idNavio = idNavio;
	}
}
