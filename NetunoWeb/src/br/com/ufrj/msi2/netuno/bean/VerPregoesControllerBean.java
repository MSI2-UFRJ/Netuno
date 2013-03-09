package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.logistica.servicos.LogisticaService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteLogistica;

@ManagedBean(name="verPregoesController")
@ViewScoped
public class VerPregoesControllerBean extends MBean{

	@EJB
	LogisticaService logisticaService;
	
	public LogisticaService getLogisticaService() {
		return logisticaService;
	}

	public void setLogisticaService(LogisticaService logisticaService) {
		this.logisticaService = logisticaService;
	}

	public VerPregoesModelBean getVerPregoesModelBean() {
		return verPregoesModelBean;
	}

	public void setVerPregoesModelBean(VerPregoesModelBean verPregoesModelBean) {
		this.verPregoesModelBean = verPregoesModelBean;
	}

	@ManagedProperty(value="#{verPregoesModel}")
	private VerPregoesModelBean verPregoesModelBean;
	
	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		AgenteLogistica agentelogistica = (AgenteLogistica) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		verPregoesModelBean.setAgentelogistica(agentelogistica);
		verPregoesModelBean.setPregoes(logisticaService.recuperaPregoesAbertos());
	}
}
