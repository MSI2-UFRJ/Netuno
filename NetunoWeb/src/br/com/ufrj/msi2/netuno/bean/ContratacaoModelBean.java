package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;

@ManagedBean(name="contratacaoModel")
@ViewScoped
public class ContratacaoModelBean extends MBean {
	
	private Contratante contratante;
	
	@PostConstruct
	public void construct() {
		
	}

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}
	
}
