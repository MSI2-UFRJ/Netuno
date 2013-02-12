package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="contratante")
@PrimaryKeyJoinColumn(name="id")
public class Contratante extends Usuario implements Serializable {
	private static final long serialVersionUID = -837838116953606430L;

	private Integer id;
	
	@OneToMany
	@JoinColumn(name="contratante_id")
	private List<Contrato> contrato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
