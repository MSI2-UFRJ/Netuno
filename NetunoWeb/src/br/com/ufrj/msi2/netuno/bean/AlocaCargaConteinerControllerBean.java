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

@ManagedBean(name="alocaCargaConteinerController")
@ViewScoped
public class AlocaCargaConteinerControllerBean extends MBean {
	@EJB
	public GerenciarCargasService gCargaService;
	private AgenteCarga agente;
	private int cargaId = 0;

	@ManagedProperty(value="#{alocaCargaConteinerModel}")
	private AlocaCargaConteinerModelBean alocaCargaConteinerModelBean;
	
	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		
	}
	
	public void onPageLoad()
	{
		if(cargaId > 0)
		{
			alocaCargaConteinerModelBean.setCarga(gCargaService.obterPorId(cargaId));
			
			//alocaCargaConteinerModelBean.setListDisponiveis(gCargaService.listaConteinersDisponiveis());
		}
	}
	

	public int getCargaId() {
		return cargaId;
	}

	public void setCargaId(int cargaId) {
		this.cargaId = cargaId;
	}
	
	public AlocaCargaConteinerModelBean getAlocaCargaConteinerModelBean() {
		return alocaCargaConteinerModelBean;
	}

	public void setAlocaCargaConteinerModelBean(
			AlocaCargaConteinerModelBean alocaCargaConteinerModelBean) {
		this.alocaCargaConteinerModelBean = alocaCargaConteinerModelBean;
	}

}
