package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;


@ManagedBean(name="listarConteinersModel")
@ViewScoped
public class ListaConteinersModelBean {

	private List<Conteiner> listaConteiners;
	
	@PostConstruct
	public void construct() {
		
	}

	public void setListaConteiners(List<Conteiner> listaConteiners) {
		this.listaConteiners = listaConteiners;
	}

	public List<Conteiner> getListaConteiners() {
		return listaConteiners;
	}
	
}
