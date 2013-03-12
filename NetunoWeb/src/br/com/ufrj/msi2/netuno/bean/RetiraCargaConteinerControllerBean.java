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
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarCargasService;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarConteinersService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;

@ManagedBean(name = "retiraCargaConteinerController")
@ViewScoped
public class RetiraCargaConteinerControllerBean extends MBean {
	@EJB
	public GerenciarCargasService gCargaService;

	@EJB
	public GerenciarConteinersService gConteinerService;

	private AgenteCarga agente;

	private int cargaId = 0;
	
	@ManagedProperty(value = "#{retiraCargaConrteinerModel}")
	private RetiraCargaConteinerModelBean retiraAlocaCargaConteinerModelBean;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		agente = (AgenteCarga) session
				.getAttribute(Attributes.SessionAttributes.LOGIN.toString());

	}

	public void onPageLoad() {
		if (cargaId > 0) {
			this.getInformations();
		}
	}

	private void getInformations() {
//		reti
//				.setCarga(gCargaService.obterPorId(cargaId));
//		alocaCargaConteinerModelBean.setListDisponiveis(gConteinerService
//				.listaConteinersDisponiveis(
//						alocaCargaConteinerModelBean.getCarga(),
//						agente.getPertence()));
	}

	public int getCargaId() {
		return cargaId;
	}

	public void setCargaId(int cargaId) {
		this.cargaId = cargaId;
	}

	public RetiraCargaConteinerModelBean getRetiraCargaConteinerModelBean() {
		return retiraAlocaCargaConteinerModelBean;
	}

	public void setRetiraCargaConteinerModelBean(
			RetiraCargaConteinerModelBean retiraAlocaCargaConteinerModelBean) {
		this.retiraAlocaCargaConteinerModelBean = retiraAlocaCargaConteinerModelBean;
	}

	public void alocarCarga() {
		boolean valida = true;
		if (retiraAlocaCargaConteinerModelBean.getConteinerSelecionado() == 0) {
			valida = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Selecione um conteiner.", null);
		}

		if (valida) {
			Conteiner conteiner = gConteinerService
					.obterPorId(retiraAlocaCargaConteinerModelBean
							.getConteinerSelecionado());
			gCargaService.alocarCarga(retiraAlocaCargaConteinerModelBean.getCarga(),
					conteiner);

			super.sendMessage(null, FacesMessage.SEVERITY_INFO,
					"Carga Alocada Com Sucesso !", null);

			// this.getInformations();

		}
	}

	public String cancelar() {
		return "listaCarga";
	}

	public AgenteCarga getAgente() {
		return agente;
	}

	public void setAgente(AgenteCarga agente) {
		this.agente = agente;
	}
}
