package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.contratacao.servicos.ContratacaoService;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@ManagedBean(name="verContratosController")
@ViewScoped
public class VerContratosControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@ManagedProperty(value="#{verContratosModel}")
	private VerContratosModelBean verContratosModelBean;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Contratante contratante = (Contratante) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		verContratosModelBean.setContratante(contratante);
		verContratosModelBean.setListaContratos(this.contratacaoService.recuperaContratosPorContratante(contratante));
	}

	public String verDetalhes(Contrato contrato) {
		return "";
	}

	public VerContratosModelBean getVerContratosModelBean() {
		return verContratosModelBean;
	}

	public void setVerContratosModelBean(VerContratosModelBean verContratosModelBean) {
		this.verContratosModelBean = verContratosModelBean;
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public void setContratacaoService(ContratacaoService contratacaoService) {
		this.contratacaoService = contratacaoService;
	}
	
}
