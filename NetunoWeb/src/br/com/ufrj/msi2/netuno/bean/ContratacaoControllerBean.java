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
import br.com.ufrj.msi2.netuno.contratacao.servicos.ContratacaoService;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaPerecivel;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.enums.FormaPagamentoEnum;
import br.com.ufrj.msi2.netuno.modelo.servicos.PortoService;

@ManagedBean(name="contratacaoController")
@ViewScoped
public class ContratacaoControllerBean extends MBean {

	@EJB
	ContratacaoService contratacaoService;

	@EJB
	private PortoService portoService;

	@ManagedProperty(value="#{contratacaoModel}")
	private ContratacaoModelBean contratacaoModelBean;

	private boolean deveRedirecionarParaTelaDeAtendente;
	
	private ResourceBundle contratacaoBundle;

	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Contratante contratante = (Contratante) session.getAttribute(Attributes.SessionAttributes.CONTRATANTE.toString());
		contratacaoModelBean.setContratante(contratante);
		
		if(session.getAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString()) == null) {
			this.deveRedirecionarParaTelaDeAtendente = false;
		} else {
			if(session.getAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString()).toString() == "atendente") {
				this.deveRedirecionarParaTelaDeAtendente = true;
			}
		}
		
		if(session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString()) == null) {
			contratacaoModelBean.setContrato(this.contratacaoService.criarContrato());
			contratacaoModelBean.getContrato().setCargas(new ArrayList<Carga>());
		} else {
			Contrato contrato = (Contrato) session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString());
			Contrato contratoComCargas;
			
			if(session.getAttribute(Attributes.SessionAttributes.CARREGACARGAS.toString()) != null) {
				contratoComCargas = contratacaoService.recuperaContratoComCargas(contrato);
				session.removeAttribute(Attributes.SessionAttributes.CARREGACARGAS.toString());
				
				List<CargaLog> logs = new ArrayList<CargaLog>();

				for(Carga carga : contratoComCargas.getCargas()) {
					CargaLog cargaLog = this.contratacaoService.recuperaUltimoCargaLogDeCarga(carga);
					
					if(cargaLog == null) {
						cargaLog = new CargaLog();
					}
					
					logs.add(cargaLog);
				}
				
				contratacaoModelBean.setLogs(logs);
				contratacaoModelBean.setModoVerDetalhes(true);
			} else {
				contratoComCargas = contrato;
			}

			contratacaoModelBean.setContrato(contratoComCargas);
			
			if(contratacaoModelBean.getContrato().getEnderecoColeta() != null) {
				contratacaoModelBean.setEnderecoColeta(true);
			}
			
			if(contratacaoModelBean.getContrato().getEnderecoEntrega() != null) {
				contratacaoModelBean.setEnderecoEntrega(true);
			}
		}

		contratacaoModelBean.setPortos(this.portoService.obterTodos());
		contratacaoModelBean.setFormasPagamento(FormaPagamentoEnum.values());
		
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "contratacaoMsg");
		this.contratacaoBundle = bundle;
	}
	
	/**
	 * Chama m�todo do service para estimar data de entrega das cargas do contrato.
	 */
	public void atualizarPrazo() {
		this.contratacaoService.estimarDataEntrega(contratacaoModelBean.getContrato(), contratacaoModelBean.isEnderecoColeta(), contratacaoModelBean.isEnderecoEntrega());
	}
	
	/**
	 * Adiciona Carga ao Contrato
	 * @param isCargaPerecivel boolean. Se true, adiciona CargaPerecivel. Caso contr�rio, adiciona Carga.
	 */
	public void adicionarCarga(boolean isCargaPerecivel) {
		Carga carga;
		
		if(isCargaPerecivel) {
			carga = new CargaPerecivel();
		} else {
			carga = new Carga();
		}

		contratacaoModelBean.getContrato().getCargas().add(carga);
		carga.setContrato(contratacaoModelBean.getContrato());
	}
	
	/**
	 * Remove carga do contrato.
	 * @param carga escolhida pelo usu�rio
	 */
	public void removerCarga(Carga carga) {
		contratacaoModelBean.getContrato().getCargas().remove(carga);
	}
	
	/**
	 * Valida dados do contrato e redireciona para a tela de fechar contrato.
	 * @return String de navega��o para a tela de fechar contrato.
	 */
	public String avancarParaFecharContrato() {
		boolean valida = true;
		
		if(!contratacaoModelBean.isEnderecoColeta()) {
			contratacaoModelBean.getContrato().setEnderecoColeta(null);
		}
		
		if(!contratacaoModelBean.isEnderecoEntrega()) {
			contratacaoModelBean.getContrato().setEnderecoEntrega(null);
		}

		if(contratacaoModelBean.getContrato().getPortoOrigem().equals(contratacaoModelBean.getContrato().getPortoDestino())) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString("contratacao.msg_portoOrigemEDestinoIguais"), null);
			valida = false;
		}
		
		if(contratacaoModelBean.getContrato().getCargas().size() == 0) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString("contratacao.msg_erroSemCarga"), null);
			valida = false;
		}
		
		for(Carga carga : contratacaoModelBean.getContrato().getCargas()) {
			if(carga.getDataValidade() != null) {
				if(carga.getDataValidade().before(contratacaoModelBean.getContrato().getDataEstimada())) {
					super.sendMessage(null, FacesMessage.SEVERITY_ERROR, this.contratacaoBundle.getString("contratacao.msg_cargaPerecivelExpira"), null);
					valida = false;
				}
			}
		}

		if(valida) {
			this.contratacaoService.calcularPreco(contratacaoModelBean.getContrato(), contratacaoModelBean.isEnderecoColeta(), contratacaoModelBean.isEnderecoEntrega());
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute(Attributes.SessionAttributes.CONTRATO.toString(), contratacaoModelBean.getContrato());
			return "fecharContrato";
		}
		
		return null;
	}
	
	/**
	 * Redireciona para a tela inicial do usu�rio logado (Contratante ou Atendente).
	 * @return String de navega��o.
	 */
	public String cancelar() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute(Attributes.SessionAttributes.CONTRATANTE.toString());
		session.removeAttribute(Attributes.SessionAttributes.CONTRATO.toString());
		session.removeAttribute(Attributes.SessionAttributes.VIAATENDENTE.toString());
		
		if(this.deveRedirecionarParaTelaDeAtendente) {
			return "telaAtendente";
		} else {
			return "verContratos"; 
		}
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public PortoService getPortoService() {
		return portoService;
	}

	public ContratacaoModelBean getContratacaoModelBean() {
		return contratacaoModelBean;
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

	public void setPortoService(PortoService portoService) {
		this.portoService = portoService;
	}

	public void setContratacaoModelBean(ContratacaoModelBean contratacaoModelBean) {
		this.contratacaoModelBean = contratacaoModelBean;
	}

	public void setDeveRedirecionarParaTelaDeAtendente(
			boolean deveRedirecionarParaTelaDeAtendente) {
		this.deveRedirecionarParaTelaDeAtendente = deveRedirecionarParaTelaDeAtendente;
	}

	public void setContratacaoBundle(ResourceBundle contratacaoBundle) {
		this.contratacaoBundle = contratacaoBundle;
	}
	
}
