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
import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;

@ManagedBean(name="atendenteController")
@ViewScoped
public class AtendenteControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@ManagedProperty(value="#{atendenteModel}")
	private AtendenteModelBean atendenteModelBean;
	
	@PostConstruct
	public void construct() {
		atendenteModelBean.setCpf(new CPF());
	}
	
	/**
	 * Busca contratante com o CPF digitado pelo atendente. Caso encontre, redireciona para tela de contrata��o.
	 * @return String de navega��o.
	 */
	public String verificarCPF() {
		try {
			Contratante contratante = this.contratacaoService.recuperaContratantePorCPF(atendenteModelBean.getCpf());
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute(Attributes.SessionAttributes.CONTRATANTE.toString(), contratante);
			session.setAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString(), "atendente");
			
			return "contratacao";
		} catch (ResultadoNaoEncontradoException e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Contratante n�o encontrado.", null);
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * Redireciona para a tela de cadastro de contratante.
	 * @return String de navega��o
	 */
	public String irCadastro() {
		return "novoContratante";
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public AtendenteModelBean getAtendenteModelBean() {
		return atendenteModelBean;
	}

	public void setContratacaoService(ContratacaoService contratacaoService) {
		this.contratacaoService = contratacaoService;
	}

	public void setAtendenteModelBean(AtendenteModelBean atendenteModelBean) {
		this.atendenteModelBean = atendenteModelBean;
	}
	
}
