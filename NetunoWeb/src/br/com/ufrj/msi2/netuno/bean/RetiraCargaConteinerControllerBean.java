package br.com.ufrj.msi2.netuno.bean;

import java.util.ArrayList;
import java.util.List;
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
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;

@ManagedBean(name = "retiraCargaConteinerController")
@ViewScoped
public class RetiraCargaConteinerControllerBean extends MBean {
	@EJB
	public GerenciarCargasService gCargaService;

	@EJB
	public GerenciarConteinersService gConteinerService;

	private AgenteCarga agente;
	private ResourceBundle bundle;


	private int cargaId = 0;
	
	@ManagedProperty(value = "#{retiraCargaConteinerModel}")
	private RetiraCargaConteinerModelBean retiraCargaConteinerModelBean;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());

		FacesContext context = FacesContext.getCurrentInstance();
		this.bundle = context.getApplication().getResourceBundle(context, "cargaMsg");

	}

	public void onPageLoad() {
		if (cargaId > 0) {
			this.getInformations();
		}
	}

	/**
	 * Recupera informações necessárias para montar a página de Retirada de Carga 
	 */
	private void getInformations() {
		retiraCargaConteinerModelBean.setCarga(gCargaService.obterPorId(cargaId));
		
		retiraCargaConteinerModelBean.setListPartes(
				this.trataListParteCargaDesembarque(
						gCargaService.listaParteCargasComConteiner(
								retiraCargaConteinerModelBean.getCarga()
								)
						)
				);
		
	}
	
	/**
	 * Filtra a lista de PartesCarga por cargas que estão em um porto.
	 * @param list List<ParteCarga> Lista com as PartesCarga.
	 * @return List<ParteCarga> Lista filtrada por PartesCarga que estão em um porto. 
	 */
	private List<ParteCarga> trataListParteCargaDesembarque(List<ParteCarga> list)
	{
		List<ParteCarga> listResult = new ArrayList<ParteCarga>();
		for(ParteCarga parte : list)
		{
			boolean found = false;
			if(parte.getConteiner().getPorto() != null)
			{
				found = true;
			}
			if(found)
				listResult.add(parte);
		}
		
		return listResult;
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
	
	/**
	 * Retira a ParteCarga passada como parâmetro do Conteiner.
	 * @param parteId int. Id da parte carga que se deseja desembarcar.
	 */
	public void desembarcarCarga(int parteId) {
			try {
				gCargaService.desalocarParteCarga(parteId);
			} catch (Exception e) {
				super.sendMessage(null, FacesMessage.SEVERITY_ERROR,
						"Ocorreu um erro ao desembarcar a parte. Tente novamente.", null);
				return;
			}
			
			super.sendMessage(null, FacesMessage.SEVERITY_INFO,
					"Parte da Carga Desembarcada do conteiner com Sucesso!", null);			
			
			this.getInformations();
	}
	
	/**
	 * Retira a Carga passada como parâmetro do Conteiner e todas as suas ParteCargas.
	 * @param cargaId int. Id da carga que se deseja desembarcar inteira.
	 */
	public void desembarcarTodas(int cargaId) {
		try {
			gCargaService.desalocarTodasPartes(cargaId);
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro ao desembarcar a carga completamente. Tente novamente.", null);
			return;
		}
		
		super.sendMessage(null, FacesMessage.SEVERITY_INFO,
				"Carga completamente desembarcada do conteiner com Sucesso!", null);	
		
		this.getInformations();
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
