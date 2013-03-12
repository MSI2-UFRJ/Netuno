package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;

@ManagedBean(name="listaCargasAgenteModel")
@ViewScoped
public class ListaCargasAgenteModelBean extends MBean {

	private List<Carga> listaCargas;
	
	@PostConstruct
	public void construct() {
	}
	
	public List<Carga> getListaCargas() {
		return listaCargas;
	}

	public void setListaCargas(List<Carga> listaCargas) {
		this.listaCargas = listaCargas;
	}
}
