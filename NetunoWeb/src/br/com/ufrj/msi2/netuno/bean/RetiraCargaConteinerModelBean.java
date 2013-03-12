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
		pesoCargaRestante = this.carga.getPeso();
		for (ParteCarga parte : carga.getPartes()) {
			pesoCargaRestante -= parte.getPeso();
		}
		
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
		return pesoCargaRestante;
	}

	public void setPesoCargaRestante(double pesoCargaRestante) {
		this.pesoCargaRestante = pesoCargaRestante;
	}

}
