package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarNaviosService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;

@ManagedBean(name="listaNaviosAgenteController")
@ViewScoped
public class ListaNaviosAgenteControllerBean extends MBean {

	@EJB
	public GerenciarNaviosService navioService;
	
	private AgenteCarga agente;

	@ManagedProperty(value="#{listaNaviosAgenteModel}")
	private ListaNaviosAgenteModelBean listaNaviosAgenteModelBean;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
	}
	
	public void onPageLoad()
	{
		listaNaviosAgenteModelBean.setListaNavios(this.navioService.listaNaviosComCarga(agente));
	}
	
	public GerenciarNaviosService getNavioService() {
		return navioService;
	}

	public void setNavioService(GerenciarNaviosService navioService) {
		this.navioService = navioService;
	}

	public ListaNaviosAgenteModelBean getListaNaviosAgenteModelBean() {
		return listaNaviosAgenteModelBean;
	}

	public void setNaviosAgenteModelBean(ListaNaviosAgenteModelBean ListaNaviosAgenteModelBean){
		this.listaNaviosAgenteModelBean = ListaNaviosAgenteModelBean;
	}
	

	public AgenteCarga getAgente() {
		return agente;
	}

	public void setAgente(AgenteCarga agente) {
		this.agente = agente;
	}
}
