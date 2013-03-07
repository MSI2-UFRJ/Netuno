package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoService;

@ManagedBean(name="verContratosController")
@ViewScoped
public class VerContratosControllerBean extends MBean {
	
	@EJB
	ContratoService contratoService;
	
	@ManagedProperty(value="#{verContratosModel}")
	private VerContratosModelBean verContratosModelBean;
	
	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Usuario usuario = (Usuario) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		verContratosModelBean.setUsuario(usuario);
		verContratosModelBean.setListaContratos(this.contratoService.getContratosPorContratante((Contratante) usuario));
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

	public ContratoService getContratoService() {
		return contratoService;
	}

	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}
	
}
