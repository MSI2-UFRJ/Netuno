package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;

@ManagedBean(name="verContratosController")
@ViewScoped
public class VerContratosControllerBean extends MBean {
	
	@ManagedProperty(value="#{verContratosModel}")
	private VerContratosModelBean verContratosModelBean;
	
	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		verContratosModelBean.setUsuario((Usuario) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString()));
	}

	public VerContratosModelBean getVerContratosModelBean() {
		return verContratosModelBean;
	}

	public void setVerContratosModelBean(VerContratosModelBean verContratosModelBean) {
		this.verContratosModelBean = verContratosModelBean;
	}
	
}
