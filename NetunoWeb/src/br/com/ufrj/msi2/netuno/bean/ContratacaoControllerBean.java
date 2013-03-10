package br.com.ufrj.msi2.netuno.bean;

import java.util.ArrayList;

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
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaPerecivel;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
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
		contratacaoModelBean.setContrato(this.contratacaoService.criarContrato());
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
		
		if(contratacaoModelBean.getContrato().getCargas() == null) {
			contratacaoModelBean.getContrato().setCargas(new ArrayList<Carga>());
		}

		contratacaoModelBean.getContrato().getCargas().add(carga);
		carga.setContrato(contratacaoModelBean.getContrato());
	}
	
	public void removerCarga(Carga carga) {
		contratacaoModelBean.getContrato().getCargas().remove(carga);
	}
	
	public String salvar() {
		if(!contratacaoModelBean.isEnderecoColeta()) {
			contratacaoModelBean.getContrato().setEnderecoColeta(null);
		}
		
		if(!contratacaoModelBean.isEnderecoEntrega()) {
			contratacaoModelBean.getContrato().setEnderecoEntrega(null);
		}
		
		contratacaoService.salvarContrato(contratacaoModelBean.getContratante(), contratacaoModelBean.getContrato());
		
		super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Contrato criado com sucesso", null);

		return "verContratos";
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
