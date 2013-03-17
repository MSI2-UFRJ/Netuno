package br.com.ufrj.msi2.netuno.bean;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.contratacao.servicos.ContratacaoService;
import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ValidacaoException;

@ManagedBean(name="contratanteController")
@ViewScoped
public class ContratanteControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@ManagedProperty(value="#{contratanteModel}")
	private ContratanteModelBean contratanteModelBean;
	
	private ResourceBundle contratacaoBundle;

	@PostConstruct
	public void construct() {
		Contratante contratante = this.contratacaoService.criarContratante();
		contratante.setCpf(new CPF());
		contratanteModelBean.setContratante(contratante);
		
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "contratacaoMsg");
		this.contratacaoBundle = bundle;
	}

	/**
	 * Valida contratante e o cria, se validado. Redireciona para tela de contratação, com o contratante recém criado.
	 * @return String de navegação
	 */
	public String criar() {
		boolean contratanteValido = true;

		Contratante contratante = contratanteModelBean.getContratante();

		if(!contratante.getSenha().equals(contratanteModelBean.getConfirmacaoSenha())) {
			contratanteValido = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString("atendente.msg_senhasDiferentes"), null);
		}

		if(contratacaoService.existeUsuarioComLogin(contratante.getLogin())) {
			contratanteValido = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString("atendente.msg_loginUsado"), null);
		}

		if(contratacaoService.existeUsuarioComCPF(contratante.getCpf())) {
			contratanteValido = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString("atendente.msg_CPFUsado"), null);
		}

		if(contratanteValido) {
			try {
				contratacaoService.salvarContratante(contratanteModelBean.getContratante());
			} catch (ValidacaoException e) {
				super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString(e.getMessage()), null);
				return null;
			}
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute(Attributes.SessionAttributes.CONTRATANTE.toString(), contratanteModelBean.getContratante());
			
			if(session.getAttribute(Attributes.SessionAttributes.USUARIOSECADASTRANDO.toString()) == null) {
				session.setAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString(), "atendente");
			} else {
				session.removeAttribute(Attributes.SessionAttributes.USUARIOSECADASTRANDO.toString());
				session.setAttribute(Attributes.SessionAttributes.LOGIN.toString(), contratanteModelBean.getContratante());
			}
			
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, this.contratacaoBundle.getString("atendente.msg_contratanteCriado"), null);
			return "contratacao";
		}
		
		return null;
	}
	
	/**
	 * Redireciona para tela inicial de atendente.
	 * @return String de navegação.
	 */
	public String cancelar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		if(session.getAttribute(Attributes.SessionAttributes.USUARIOSECADASTRANDO.toString()) == null) {
			return "atendente";
		} else {
			session.removeAttribute(Attributes.SessionAttributes.USUARIOSECADASTRANDO.toString());
			return "login";
		}
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public ContratanteModelBean getContratanteModelBean() {
		return contratanteModelBean;
	}

	public ResourceBundle getContratacaoBundle() {
		return contratacaoBundle;
	}

	public void setContratacaoService(ContratacaoService contratacaoService) {
		this.contratacaoService = contratacaoService;
	}

	public void setContratanteModelBean(ContratanteModelBean contratanteModelBean) {
		this.contratanteModelBean = contratanteModelBean;
	}

	public void setContratacaoBundle(ResourceBundle contratacaoBundle) {
		this.contratacaoBundle = contratacaoBundle;
	}	

}
