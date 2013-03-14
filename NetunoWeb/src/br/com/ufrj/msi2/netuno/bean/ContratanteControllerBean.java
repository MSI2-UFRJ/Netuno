package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.contratacao.servicos.ContratacaoService;
import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;

@ManagedBean(name="contratanteController")
@ViewScoped
public class ContratanteControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@ManagedProperty(value="#{contratanteModel}")
	private ContratanteModelBean contratanteModelBean;

	@PostConstruct
	public void construct() {
		Contratante contratante = this.contratacaoService.criarContratante();
		contratante.setCpf(new CPF());
		contratanteModelBean.setContratante(contratante);
	}

	public String criar() {
		boolean contratanteValido = true;

		Contratante contratante = contratanteModelBean.getContratante();

		if(!contratante.getSenha().equals(contratanteModelBean.getConfirmacaoSenha())) {
			contratanteValido = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Senhas não correspondem", null);
		}

		if(contratacaoService.existeUsuarioComLogin(contratante.getLogin())) {
			contratanteValido = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Já existe usuário com esse login", null);
		}

		if(contratacaoService.existeUsuarioComCPF(contratante.getCpf())) {
			contratanteValido = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Já existe usuário com esse CPF", null);
		}

		if(contratanteValido) {
			contratacaoService.salvarContratante(contratanteModelBean.getContratante());
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Contratante criado com sucesso", null);
			return "atendente";
		}
		
		return null;
	}
	
	public String cancelar() {
		return "atendente";
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public ContratanteModelBean getContratanteModelBean() {
		return contratanteModelBean;
	}

	public void setContratacaoService(ContratacaoService contratacaoService) {
		this.contratacaoService = contratacaoService;
	}

	public void setContratanteModelBean(ContratanteModelBean contratanteModelBean) {
		this.contratanteModelBean = contratanteModelBean;
	}
	
}
