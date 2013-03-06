package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="empresatransporte")
public class EmpresaTransporte implements Serializable{

	private static final long serialVersionUID = -4716807779986635045L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@OneToMany
	@JoinColumn(name="empresatransporte_id")
	private List<RepresEmpTrans> representantes;

	public List<RepresEmpTrans> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<RepresEmpTrans> representantes) {
		this.representantes = representantes;
	}
}
