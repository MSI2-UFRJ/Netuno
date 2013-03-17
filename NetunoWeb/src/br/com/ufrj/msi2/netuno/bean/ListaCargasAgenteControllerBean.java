package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarCargasService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;

@ManagedBean(name="listaCargasAgenteController")
@ViewScoped
public class ListaCargasAgenteControllerBean extends MBean {

	@EJB
	public GerenciarCargasService cargaService;

	private boolean embarque;
	private AgenteCarga agente;

	@ManagedProperty(value="#{listaCargasAgenteModel}")
	private ListaCargasAgenteModelBean listaCargasAgenteModelBean;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
	}
	
	public void onPageLoad()
	{
		if(this.isEmbarque())
		{
			listaCargasAgenteModelBean.setListaCargas(this.cargaService.listaCargasParaEmbarque(agente));
		}
		else
		{
			listaCargasAgenteModelBean.setListaCargas(this.cargaService.listaCargasParaDesembarque(agente));
		}
		
	}
	
	public GerenciarCargasService getCargaService() {
		return cargaService;
	}

	public void setCargaService(GerenciarCargasService cargaService) {
		this.cargaService = cargaService;
	}

	public ListaCargasAgenteModelBean getListaCargasAgenteModelBean() {
		return listaCargasAgenteModelBean;
	}

	public void setListaCargasAgenteModelBean(
			ListaCargasAgenteModelBean listaCargasAgenteModelBean) {
		this.listaCargasAgenteModelBean = listaCargasAgenteModelBean;
	}
	

	public AgenteCarga getAgente() {
		return agente;
	}

	public void setAgente(AgenteCarga agente) {
		this.agente = agente;
	}
	
	/**
	 * Verifica se a listagem de cargas mostrada são cargas de Embarque ou Desembarque
	 */
	public boolean isEmbarque() {
		return embarque;
	}

	/**
	 * Seta variável que verifica se a cargas mostradas são de Embarque ou Desembarque.
	 */
	public void setEmbarque(boolean isEmbarque) {
		this.embarque = isEmbarque;
	}

}
