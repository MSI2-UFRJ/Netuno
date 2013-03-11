package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

@ManagedBean(name="listaNaviosAgenteModel")
@ViewScoped
public class ListaNaviosAgenteModelBean extends MBean {

	private List<Navio> listaNavios;

	@PostConstruct
	public void construct() {
		
	}
	
	public List<Navio> getListaNavios() {
		return listaNavios;
	}

	public void setListaNavios(List<Navio> listaNavios) {
		this.listaNavios = listaNavios;
	}

}
