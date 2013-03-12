package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@ManagedBean(name="contratacaoModel")
@ViewScoped
public class ContratacaoModelBean extends MBean {
	
	private Contratante contratante;
	
	private Contrato contrato;
	
	private boolean enderecoColeta;
	
	private boolean enderecoEntrega;
	
	private boolean modoVerDetalhes;
	
	private List<Porto> portos;
	
	private List<CargaLog> logs;

	@PostConstruct
	public void construct() {
		
	}

	public Contratante getContratante() {
		return contratante;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public boolean isEnderecoColeta() {
		return enderecoColeta;
	}

	public boolean isEnderecoEntrega() {
		return enderecoEntrega;
	}

	public boolean isModoVerDetalhes() {
		return modoVerDetalhes;
	}

	public List<Porto> getPortos() {
		return portos;
	}

	public List<CargaLog> getLogs() {
		return logs;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public void setEnderecoColeta(boolean enderecoColeta) {
		this.enderecoColeta = enderecoColeta;
	}

	public void setEnderecoEntrega(boolean enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public void setModoVerDetalhes(boolean modoVerDetalhes) {
		this.modoVerDetalhes = modoVerDetalhes;
	}

	public void setPortos(List<Porto> portos) {
		this.portos = portos;
	}

	public void setLogs(List<CargaLog> logs) {
		this.logs = logs;
	}

}
