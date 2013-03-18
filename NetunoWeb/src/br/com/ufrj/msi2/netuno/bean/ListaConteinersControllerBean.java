package br.com.ufrj.msi2.netuno.bean;

import java.util.ResourceBundle;

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
	
	private ResourceBundle bundle;
	
	@ManagedProperty(value="#{listaConteinersModel}")
	private ListaConteinersModelBean listaConteinersModelBean;
	
	@PostConstruct
	public void construct(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		agente = (AgenteCarga) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		FacesContext context = FacesContext.getCurrentInstance();
		this.bundle = context.getApplication().getResourceBundle(context, "cargaMsg");
	}
	
	public void onPageLoad()
	{
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Navio navio = (Navio) session.getAttribute(Attributes.SessionAttributes.NAVIO.toString());
		listaConteinersModelBean.setListaConteiners(this.conteinerService.recuperarPorNavioPorAgenteParaDesembarque(navio, agente));
	}
	
	public String listaConteinersNavio(Navio navio){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute(Attributes.SessionAttributes.NAVIO.toString(), navio);
		
		return "desembarcaConteiners";
	}
	
	public String desembarcarConteiner(Conteiner conteiner){
		conteinerService.desembarcarConteiner(conteiner, agente.getPertence());
		return "desembarcaConteiners";
	}
	
	public String embarcarConteiner(Conteiner conteiner, Navio navio){
		conteinerService.embarcarConteiner(conteiner, navio);
		return "embarcaConteiners";
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
	

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
}
