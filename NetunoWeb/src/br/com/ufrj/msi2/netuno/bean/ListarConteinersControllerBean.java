package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.carga.servicos.GerenciarConteinersService;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.servicos.ConteinerService;

@ManagedBean(name="listarConteinerController")
@ViewScoped
public class ListarConteinersControllerBean {

	@EJB
	GerenciarConteinersService conteinerService;
	
	@ManagedProperty(value="#{listarConteinersModel}")
	private ListarConteinersModelBean listarConteinersModel;
	
	
	@PostConstruct
	public void construct(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Navio navio = (Navio) session.getAttribute(Attributes.SessionAttributes.NAVIO.toString());
		listarConteinersModel.setListaConteiners(this.conteinerService.recuperarConteinersPorNavio(navio));
		
	}
	
	public String listaConteinersNavio(Navio navio){
		//TODO: listar só os conteiners do navio passado
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute(Attributes.SessionAttributes.NAVIO.toString(), navio);
		
		return "listaConteinersNavio";
	}

	public GerenciarConteinersService getConteinerService() {
		return conteinerService;
	}

	public ListarConteinersModelBean getListarConteinersModel() {
		return listarConteinersModel;
	}

	public void setConteinerService(GerenciarConteinersService conteinerService) {
		this.conteinerService = conteinerService;
	}

	public void setListarConteinersModel(
			ListarConteinersModelBean listarConteinersModel) {
		this.listarConteinersModel = listarConteinersModel;
	}

}
