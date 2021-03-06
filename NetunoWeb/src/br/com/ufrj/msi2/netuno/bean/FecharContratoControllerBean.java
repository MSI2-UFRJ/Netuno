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
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.enums.FormaPagamentoEnum;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ValidacaoException;

@ManagedBean(name="fecharContratoController")
@ViewScoped
public class FecharContratoControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@ManagedProperty(value="#{fecharContratoModel}")
	private FecharContratoModelBean fecharContratoModelBean;

	private boolean deveRedirecionarParaTelaDeAtendente;
	
	private ResourceBundle contratacaoBundle;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Contratante contratante = (Contratante) session.getAttribute(Attributes.SessionAttributes.CONTRATANTE.toString());
		fecharContratoModelBean.setContratante(contratante);
		
		if(session.getAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString()) == null) {
			this.deveRedirecionarParaTelaDeAtendente = false;
		} else {
			if(session.getAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString()).toString() == "atendente") {
				this.deveRedirecionarParaTelaDeAtendente = true;
			}
		}
		
		Contrato contrato = (Contrato) session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString());
		fecharContratoModelBean.setContrato(contrato);
		fecharContratoModelBean.setFormasPagamento(FormaPagamentoEnum.values());
		
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "contratacaoMsg");
		this.contratacaoBundle = bundle;
	}
	
	/**
	 * Salva contrato e redireciona para a tela inicial do usu�rio logado (Contratante ou Atendente).
	 * @return String de navega��o.
	 */
	public String fecharContrato() {
		try {
			contratacaoService.salvarContrato(fecharContratoModelBean.getContratante(), fecharContratoModelBean.getContrato());
		} catch (ValidacaoException e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString(e.getMessage()), null);
			return null;
		}
		
		super.sendMessage(null, FacesMessage.SEVERITY_INFO, this.contratacaoBundle.getString("contratacao.msg_contratoCriadoComSucesso"), null);
		limpaSession();
		return navegacao();
	}
	
	/**
	 * Retorna para tela de contrata��o
	 * @return String de navega��o.
	 */
	public String voltar() {
		return "contratacao";
	}
	
	/**
	 * Redireciona para a tela inicial do usu�rio logado (Contratante ou Atendente).
	 * @return String de navega��o.
	 */
	public String cancelar() {
		limpaSession();
		return navegacao();
	}
	
	public void limpaSession() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute(Attributes.SessionAttributes.CONTRATANTE.toString());
		session.removeAttribute(Attributes.SessionAttributes.CONTRATO.toString());
		session.removeAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString());
	}
	
	public String navegacao() {
		if(this.deveRedirecionarParaTelaDeAtendente) {
			return "telaAtendente";
		} else {
			return "verContratos"; 
		}
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public FecharContratoModelBean getFecharContratoModelBean() {
		return fecharContratoModelBean;
	}

	public boolean isDeveRedirecionarParaTelaDeAtendente() {
		return deveRedirecionarParaTelaDeAtendente;
	}

	public ResourceBundle getContratacaoBundle() {
		return contratacaoBundle;
	}

	public void setContratacaoService(ContratacaoService contratacaoService) {
		this.contratacaoService = contratacaoService;
	}

	public void setFecharContratoModelBean(
			FecharContratoModelBean fecharContratoModelBean) {
		this.fecharContratoModelBean = fecharContratoModelBean;
	}

	public void setDeveRedirecionarParaTelaDeAtendente(
			boolean deveRedirecionarParaTelaDeAtendente) {
		this.deveRedirecionarParaTelaDeAtendente = deveRedirecionarParaTelaDeAtendente;
	}

	public void setContratacaoBundle(ResourceBundle contratacaoBundle) {
		this.contratacaoBundle = contratacaoBundle;
	}
	
}
