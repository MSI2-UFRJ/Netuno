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
@Table(name="patio")
public class Patio implements Serializable {
	private static final long serialVersionUID = 5215263739803048329L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany
	@JoinColumn(name="patio_id")  
	private List<CargaComponente> cargasComponente;

	public Integer getId() {
		return id;
	}

	public List<CargaComponente> getCargasComponente() {
		return cargasComponente;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCargasComponente(List<CargaComponente> cargasComponente) {
		this.cargasComponente = cargasComponente;
	}

}
