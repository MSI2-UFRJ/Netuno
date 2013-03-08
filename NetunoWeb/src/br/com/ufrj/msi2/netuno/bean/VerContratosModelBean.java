package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@ManagedBean(name="verContratosModel")
@ViewScoped
public class VerContratosModelBean extends MBean {
	
	private Contratante contratante;
	
	private List<Contrato> listaContratos;
	
	private int tipoContratoExibido;
	
	@PostConstruct
	public void construct() {
		this.tipoContratoExibido = 1;
	}

	public Contratante getContratante() {
		return contratante;
	}

	public List<Contrato> getListaContratos() {
		return listaContratos;
	}

	public int getTipoContratoExibido() {
		return tipoContratoExibido;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public void setListaContratos(List<Contrato> listaContratos) {
		this.listaContratos = listaContratos;
	}

	public void setTipoContratoExibido(int tipoContratoExibido) {
		this.tipoContratoExibido = tipoContratoExibido;
	}
	
}
