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
		verContratosModelBean.setListaContratos(this.contratacaoService.recuperaContratosAbertosPorContratante(contratante));
	}
	
	public void trocarTabela() {
		switch (this.verContratosModelBean.getTipoContratoExibido()) {
		case 1:
			verContratosModelBean.setListaContratos(this.contratacaoService.recuperaContratosAbertosPorContratante(verContratosModelBean.getContratante()));
			break;
		case 2:
			verContratosModelBean.setListaContratos(this.contratacaoService.recuperaContratosFechadosPorContratante(verContratosModelBean.getContratante()));
			break;
		default:
			break;
		}
	}
	
	public String novoContrato() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute(Attributes.SessionAttributes.CONTRATANTE.toString(), verContratosModelBean.getContratante());
		
		if(session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString()) != null) {
			session.removeAttribute(Attributes.SessionAttributes.CONTRATO.toString());
		}
		
		return "contratacao";
	}

	public String verDetalhes(Contrato contrato) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute(Attributes.SessionAttributes.CONTRATANTE.toString(), verContratosModelBean.getContratante());
		session.setAttribute(Attributes.SessionAttributes.CONTRATO.toString(), contrato);
		return "contratacao";
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
