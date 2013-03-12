package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;


@ManagedBean(name="retiraCargaConteinerModel")
@ViewScoped
public class RetiraCargaConteinerModelBean extends MBean {
	
	private Carga carga;

	private List<ParteCarga> listPartes;
	
	public List<ParteCarga> getListPartes() {
		return listPartes;
	}

	public void setListPartes(List<ParteCarga> listPartes) {
		this.listPartes = listPartes;
	}

	@PostConstruct
	public void construct() {
	}
	
	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}
}
