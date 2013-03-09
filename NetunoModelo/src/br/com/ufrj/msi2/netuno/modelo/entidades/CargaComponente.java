package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Integer id;
	
	@Column(name = "peso")
	private Double peso;

	public Integer getId() {
		return id;
	}

	public Double getPeso() {
		return peso;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
