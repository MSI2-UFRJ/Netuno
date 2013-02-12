package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="partecarga")
public class ParteCarga extends CargaComponente {
	private static final long serialVersionUID = 8237313467233955825L;
	
	@ManyToOne
    private Carga carga;

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

}