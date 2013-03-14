package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@ManagedBean(name="listaRequisicoesModel")
@ViewScoped
public class ListaRequisicoesModelBean extends MBean {

		private List<Requisicao> listaRequisicoes;
		
		@PostConstruct
		public void construct(){
			
		}

		public void setListaRequisicoes(List<Requisicao> listaRequisicoes) {
			this.listaRequisicoes = listaRequisicoes;
		}

		public List<Requisicao> getListaRequisicoes() {
			return listaRequisicoes;
		}
}
