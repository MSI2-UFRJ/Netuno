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
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

@ManagedBean(name="listaConteinersController")
@ViewScoped
public class ListaConteinersControllerBean {

	@EJB
	GerenciarConteinersService conteinerService;
	
	private AgenteCarga agente;
	
	@ManagedProperty(value="#{listaConteinersModel}")
	private ListaConteinersModelBean listaConteinersModelBean;
	
	@PostConstruct
	public void construct(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
	}
	
	public void onPageLoad()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Navio navio = (Navio) session.getAttribute(Attributes.SessionAttributes.NAVIO.toString());
		listaConteinersModelBean.setListaConteiners(this.conteinerService.recuperarConteinersPorNavio(navio));
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

	public ListaConteinersModelBean getListaConteinersModelBean() {
		return listaConteinersModelBean;
	}

	public void setConteinerService(GerenciarConteinersService conteinerService) {
		this.conteinerService = conteinerService;
	}

	public void setListaConteinersModelBean(
			ListaConteinersModelBean listaConteinersModelBean) {
		this.listaConteinersModelBean = listaConteinersModelBean;
	}
	
	public String embarcarConteinerNavio(Navio navio){
		return "";
	}
}
