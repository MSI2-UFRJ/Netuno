package br.com.ufrj.msi2.netuno.bean;

import java.util.ArrayList;
import java.util.List;

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
	
	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Contratante contratante = (Contratante) session.getAttribute(Attributes.SessionAttributes.CONTRATANTE.toString());
		contratacaoModelBean.setContratante(contratante);
		
		if(session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString()) == null) {
			contratacaoModelBean.setContrato(this.contratacaoService.criarContrato());
			contratacaoModelBean.getContrato().setCargas(new ArrayList<Carga>());
		} else {
			Contrato contrato = (Contrato) session.getAttribute(Attributes.SessionAttributes.CONTRATO.toString());
			Contrato contratoComCargas = contratacaoService.recuperaContratoComCargas(contrato);
			contratacaoModelBean.setContrato(contratoComCargas);
			
			if(contratacaoModelBean.getContrato().getEnderecoColeta() != null) {
				contratacaoModelBean.setEnderecoColeta(true);
			}
			
			if(contratacaoModelBean.getContrato().getEnderecoEntrega() != null) {
				contratacaoModelBean.setEnderecoEntrega(true);
			}
			
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
		}

		contratacaoModelBean.setPortos(this.portoService.obterTodos());
	}
	
	public void atualizarPrazo() {
		this.contratacaoService.estimarDataEntrega(contratacaoModelBean.getContrato(), contratacaoModelBean.isEnderecoColeta(), contratacaoModelBean.isEnderecoEntrega());
	}
	
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
	
	public void removerCarga(Carga carga) {
		contratacaoModelBean.getContrato().getCargas().remove(carga);
	}
	
	public String salvar() {
		boolean valida = true;
		
		if(!contratacaoModelBean.isEnderecoColeta()) {
			contratacaoModelBean.getContrato().setEnderecoColeta(null);
		}
		
		if(!contratacaoModelBean.isEnderecoEntrega()) {
			contratacaoModelBean.getContrato().setEnderecoEntrega(null);
		}

		if(contratacaoModelBean.getContrato().getPortoOrigem().equals(contratacaoModelBean.getContrato().getPortoDestino())) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "O porto origem deve ser diferente do porto destino.", null);
			valida = false;
		}
		
		if(contratacaoModelBean.getContrato().getCargas().size() == 0) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Cargas devem ser adicionadas ao contrato.", null);
			valida = false;
		}
		
		for(Carga carga : contratacaoModelBean.getContrato().getCargas()) {
			if(carga.getDataValidade() != null) {
				if(carga.getDataValidade().before(contratacaoModelBean.getContrato().getDataEstimada())) {
					super.sendMessage(null, FacesMessage.SEVERITY_ERROR, "Existe carga perecível com data de validade anterior à data estimada de entrega.", null);
					valida = false;
				}
			}
		}
		
		if(valida) {
			contratacaoService.salvarContrato(contratacaoModelBean.getContratante(), contratacaoModelBean.getContrato());
			
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Contrato criado com sucesso", null);

			return "verContratos";
		}
		
		return null;
	}
	
	public String cancelar() {
		return "verContratos";
	}

	public ContratacaoService getContratacaoService() {
		return contratacaoService;
	}

	public ContratacaoModelBean getContratacaoModelBean() {
		return contratacaoModelBean;
	}

	public void setContratacaoService(ContratacaoService contratacaoService) {
		this.contratacaoService = contratacaoService;
	}

	public void setContratacaoModelBean(ContratacaoModelBean contratacaoModelBean) {
		this.contratacaoModelBean = contratacaoModelBean;
	}

	public PortoService getPortoService() {
		return portoService;
	}

	public void setPortoService(PortoService portoService) {
		this.portoService = portoService;
	}
	
}
