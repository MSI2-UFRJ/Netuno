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
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
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
	
	private int parteSelecionada;
	
	public int getParteSelecionada() {
		return parteSelecionada;
	}

	public void setParteSelecionada(int parteSelecionada) {
		this.parteSelecionada = parteSelecionada;
	}

	@ManagedProperty(value = "#{retiraCargaConrteinerModel}")
	private RetiraCargaConteinerModelBean retiraCargaConteinerModelBean;

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
		retiraCargaConteinerModelBean.setCarga(gCargaService.obterPorId(cargaId));
		retiraCargaConteinerModelBean.setListPartes(gCargaService.listaParteCargasComConteiner(
				retiraCargaConteinerModelBean.getCarga()));
	}

	public int getCargaId() {
		return cargaId;
	}

	public void setCargaId(int cargaId) {
		this.cargaId = cargaId;
	}

	public RetiraCargaConteinerModelBean getRetiraCargaConteinerModelBean() {
		return retiraCargaConteinerModelBean;
	}

	public void setRetiraCargaConteinerModelBean(
			RetiraCargaConteinerModelBean retiraCargaConteinerModelBean) {
		this.retiraCargaConteinerModelBean = retiraCargaConteinerModelBean;
	}

	public void desalocarCarga() {
		
			this.getParteSelecionada();
			
//			super.sendMessage(null, FacesMessage.SEVERITY_INFO,
//					"Parte da Carga Desalocada do conteiner com Sucesso !", null);


		}
	

	public AgenteCarga getAgente() {
		return agente;
	}

	public void setAgente(AgenteCarga agente) {
		this.agente = agente;
	}
}
