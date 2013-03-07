package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="agenteLogistica")
public class AgenteLogistica extends Usuario{

	private static final long serialVersionUID = -145546040090486072L;

	@OneToMany(mappedBy="abertoPor")
	private List<Pregao> abre;
	
	public List<Pregao> getAbre() {
		return abre;
	}

	public void setAbre(List<Pregao> abre) {
		this.abre = abre;
	}

	@Override
	public String getTipo() {
		return "agentelogistica";
	}

}
