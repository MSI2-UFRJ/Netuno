package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarRequisicoesService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

@ManagedBean(name="listarRequisicoesController")
@ViewScoped
public class ListaRequisicoesControllerBean {

	@EJB
	GerenciarRequisicoesService requisicoesService;
	
	@ManagedProperty(value="#{listarRequisicoesModel}")
	private ListaRequisicoesModelBean listarRequisicoesModel;
	
	private AgenteCarga agente;
	
	@PostConstruct
	public void construct(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		//Navio navio = (Navio) session.getAttribute(Attributes.SessionAttributes.NAVIO.toString());
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		//TODO: recuperar requisicoes por agente id e porto id
		listarRequisicoesModel.setListaRequisicoes(this.requisicoesService.recuperarTodos());
		
	}

	public GerenciarRequisicoesService getRequisicoesService() {
		return requisicoesService;
	}

	public ListaRequisicoesModelBean getListarRequisicoesModel() {
		return listarRequisicoesModel;
	}

	public void setRequisicoesService(GerenciarRequisicoesService requisicoesService) {
		this.requisicoesService = requisicoesService;
	}

	public void setListarRequisicoesModel(
			ListaRequisicoesModelBean listarRequisicoesModel) {
		this.listarRequisicoesModel = listarRequisicoesModel;
	}
}