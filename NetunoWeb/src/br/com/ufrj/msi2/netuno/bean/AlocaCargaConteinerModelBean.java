package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;


@ManagedBean(name="alocaCargaConteinerModel")
@ViewScoped
public class AlocaCargaConteinerModelBean extends MBean {
	
	private Carga carga;
	private List<ParteCarga> partes;

	private List<Conteiner> listDisponiveis;
	
	private int conteinerSelecionado;
	
	private double pesoCargaRestante = 0;

	@PostConstruct
	public void construct() {
	}
	
	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public List<Conteiner> getListDisponiveis() {
		return listDisponiveis;
	}

	public void setListDisponiveis(List<Conteiner> listDisponiveis) {
		this.listDisponiveis = listDisponiveis;
	}
	

	public int getConteinerSelecionado() {
		return conteinerSelecionado;
	}

	public void setConteinerSelecionado(int conteinerSelecionado) {
		this.conteinerSelecionado = conteinerSelecionado;
	}
	

	public double getPesoCargaRestante() {
		pesoCargaRestante = this.carga.getPeso();
		for (ParteCarga parte : partes) {
			pesoCargaRestante -= parte.getPeso();
		}
		
		return pesoCargaRestante;
	}

	public List<ParteCarga> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteCarga> partes) {
		this.partes = partes;
	}
}
