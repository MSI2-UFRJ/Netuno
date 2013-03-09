package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@ManagedBean(name="contratacaoModel")
@ViewScoped
public class ContratacaoModelBean extends MBean {
	
	private Contratante contratante;
	
	private Contrato contrato;
	
	private Boolean enderecoColeta;
	
	private Boolean enderecoEntrega;
	
	@PostConstruct
	public void construct() {
		
	}

	public Contratante getContratante() {
		return contratante;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public Boolean getEnderecoColeta() {
		return enderecoColeta;
	}

	public Boolean getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public void setEnderecoColeta(Boolean enderecoColeta) {
		this.enderecoColeta = enderecoColeta;
	}

	public void setEnderecoEntrega(Boolean enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

}
