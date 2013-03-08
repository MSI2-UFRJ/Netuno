package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteLogistica;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@ManagedBean(name="verPregoesModel")
@ViewScoped
public class VerPregoesModelBean extends MBean {
	
	private List<Pregao> pregoes;
	
	private AgenteLogistica agentelogistica;

	public AgenteLogistica getAgentelogistica() {
		return agentelogistica;
	}

	public void setAgentelogistica(AgenteLogistica agentelogistica) {
		this.agentelogistica = agentelogistica;
	}

	public List<Pregao> getPregoes() {
		return pregoes;
	}

	public void setPregoes(List<Pregao> pregoes) {
		this.pregoes = pregoes;
	}
}
