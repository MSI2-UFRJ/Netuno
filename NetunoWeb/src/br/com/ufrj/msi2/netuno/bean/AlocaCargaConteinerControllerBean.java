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
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarCargasService;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarConteinersService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;

@ManagedBean(name = "alocaCargaConteinerController")
@ViewScoped
public class AlocaCargaConteinerControllerBean extends MBean {
	@EJB
	public GerenciarCargasService gCargaService;

	@EJB
	public GerenciarConteinersService gConteinerService;

	private AgenteCarga agente;

	private ResourceBundle bundle;

	private int cargaId = 0;

	@ManagedProperty(value = "#{alocaCargaConteinerModel}")
	private AlocaCargaConteinerModelBean alocaCargaConteinerModelBean;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		agente = (AgenteCarga) session
				.getAttribute(Attributes.SessionAttributes.LOGIN.toString());

		FacesContext context = FacesContext.getCurrentInstance();
		this.bundle = context.getApplication().getResourceBundle(context, "cargaMsg");
	}

	public void onPageLoad() {
		if (cargaId > 0) {
			this.getInformations();
		}
	}

	/**
	 * Recupera informações para preencher a tela de alocação de cargas.
	 */
	private void getInformations() {
		alocaCargaConteinerModelBean
				.setCarga(gCargaService.obterPorId(cargaId));
		alocaCargaConteinerModelBean.setPartes(gCargaService.listaParteCargasComConteiner(
				alocaCargaConteinerModelBean.getCarga()));
		alocaCargaConteinerModelBean.setListDisponiveis(gConteinerService
				.listaConteinersDisponiveis(
						alocaCargaConteinerModelBean.getCarga(),agente.getPertence()));
	}

	public int getCargaId() {
		return cargaId;
	}

	public void setCargaId(int cargaId) {
		this.cargaId = cargaId;
	}

	public AlocaCargaConteinerModelBean getAlocaCargaConteinerModelBean() {
		return alocaCargaConteinerModelBean;
	}

	public void setAlocaCargaConteinerModelBean(
			AlocaCargaConteinerModelBean alocaCargaConteinerModelBean) {
		this.alocaCargaConteinerModelBean = alocaCargaConteinerModelBean;
	}
	
	/**
	 * Aloca a carga no conteiner selecionado.
	 */
	public void alocarCarga() {
		boolean valida = true;
		if (alocaCargaConteinerModelBean.getConteinerSelecionado() == 0) {
			valida = false;
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.bundle.getString("alocaCarga.SelectMessage"), null);
		}

		if (valida) {
			Conteiner conteiner = gConteinerService
					.obterPorId(alocaCargaConteinerModelBean
							.getConteinerSelecionado());
			gCargaService.alocarCarga(alocaCargaConteinerModelBean.getCarga(),
					conteiner);

			super.sendMessage(null, FacesMessage.SEVERITY_INFO,
					this.bundle.getString("alocaCarga.cargaAlocadaSucesso"), null);

			this.getInformations();

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
	

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}


}
