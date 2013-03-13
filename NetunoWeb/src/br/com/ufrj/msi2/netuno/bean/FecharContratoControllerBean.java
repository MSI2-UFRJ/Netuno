package br.com.ufrj.msi2.netuno.bean;

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

@ManagedBean(name="fecharContratoController")
@ViewScoped
public class FecharContratoControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@ManagedProperty(value="#{fecharContratoModel}")
	private FecharContratoModelBean fecharContratoModelBean;

	private boolean deveRedirecionarParaTelaDeAtendente;

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
			
			session.removeAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString());
		}
		
		
		Contrato contrato = (Contrato) session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString());
		fecharContratoModelBean.setContrato(contrato);
		fecharContratoModelBean.setFormasPagamento(FormaPagamentoEnum.values());
	}
	
	public String fecharContrato() {
		contratacaoService.salvarContrato(fecharContratoModelBean.getContratante(), fecharContratoModelBean.getContrato());
		
		super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Contrato criado com sucesso", null);
		limpaSession();
		return navegacao();
	}
	
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
	
}
