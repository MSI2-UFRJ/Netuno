package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contratante")
public class Contratante implements Serializable {
	private static final long serialVersionUID = -837838116953606430L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany
	@JoinColumn(name="contratante_id")
	private List<Contrato> contrato;

	public Integer getId() {
		return id;
	}

	public List<Contrato> getContrato() {
		return contrato;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setContrato(List<Contrato> contrato) {
		this.contrato = contrato;
	}

}
