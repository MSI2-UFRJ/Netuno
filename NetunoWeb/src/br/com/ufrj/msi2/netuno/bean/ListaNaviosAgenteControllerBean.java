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
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

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
		
		//listaNaviosAgenteModelBean.setListaNavios(this.navioService.listaNaviosComCarga(agente));
		
		listaNaviosAgenteModelBean.setListaNavios(this.navioService.recuperarNaviosPorAgenteAtracadosEmPorto(agente));
		
	}
	
	public void onPageLoad()
	{
	}

	public GerenciarNaviosService getNavioService() {
		return navioService;
	}

	public AgenteCarga getAgente() {
		return agente;
	}

	public ListaNaviosAgenteModelBean getListaNaviosAgenteModelBean() {
		return listaNaviosAgenteModelBean;
	}

	public void setNavioService(GerenciarNaviosService navioService) {
		this.navioService = navioService;
	}

	public void setAgente(AgenteCarga agente) {
		this.agente = agente;
	}

	public void setListaNaviosAgenteModelBean(
			ListaNaviosAgenteModelBean listaNaviosAgenteModelBean) {
		this.listaNaviosAgenteModelBean = listaNaviosAgenteModelBean;
	}
	
	
}
