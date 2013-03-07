package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="agentePorto")
public class AgentePorto extends Usuario{
	private static final long serialVersionUID = 137254428171726031L;

	@ManyToOne
	private Porto pertence;
	
	public Porto getPertence() {
		return pertence;
	}

	public void setPertence(Porto pertence) {
		this.pertence = pertence;
	}

	@Override
	public String getTipo() {
		return "agentePorto";
	}
	
	
}
