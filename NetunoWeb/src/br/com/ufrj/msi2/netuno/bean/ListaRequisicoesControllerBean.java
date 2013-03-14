package br.com.ufrj.msi2.netuno.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarRequisicoesService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@ManagedBean(name="listaRequisicoesController")
@ViewScoped
public class ListaRequisicoesControllerBean {

	@EJB
	GerenciarRequisicoesService requisicoesService;
	
	@ManagedProperty(value="#{listaRequisicoesModel}")
	private ListaRequisicoesModelBean listaRequisicoesModelBean;
	
	private AgenteCarga agente;
	
	@PostConstruct
	public void construct(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		//Navio navio = (Navio) session.getAttribute(Attributes.SessionAttributes.NAVIO.toString());
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		//TODO: recuperar requisicoes por agente id e porto id. da pra fazer passando so o agente
		listaRequisicoesModelBean.setListaRequisicoes(this.requisicoesService.recuperarTodos());
		
		ArrayList<Requisicao> reqs = (ArrayList<Requisicao>) listaRequisicoesModelBean.getListaRequisicoes();		
	}
	
	public String doSth(){
		return "BLA";
	}
	
	public void onPageLoad()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		//Navio navio = (Navio) session.getAttribute(Attributes.SessionAttributes.NAVIO.toString());
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		//TODO: recuperar requisicoes por agente id e porto id. da pra fazer passando so o agente
		listaRequisicoesModelBean.setListaRequisicoes(this.requisicoesService.recuperarTodos());
		
		ArrayList<Requisicao> reqs = (ArrayList<Requisicao>) listaRequisicoesModelBean.getListaRequisicoes();
	}

	public GerenciarRequisicoesService getRequisicoesService() {
		return requisicoesService;
	}

	public ListaRequisicoesModelBean getListaRequisicoesModelBean() {
		return listaRequisicoesModelBean;
	}

	public AgenteCarga getAgente() {
		return agente;
	}

	public void setRequisicoesService(GerenciarRequisicoesService requisicoesService) {
		this.requisicoesService = requisicoesService;
	}

	public void setListaRequisicoesModelBean(
			ListaRequisicoesModelBean listaRequisicoesModelBean) {
		this.listaRequisicoesModelBean = listaRequisicoesModelBean;
	}

	public void setAgente(AgenteCarga agente) {
		this.agente = agente;
	}

}
