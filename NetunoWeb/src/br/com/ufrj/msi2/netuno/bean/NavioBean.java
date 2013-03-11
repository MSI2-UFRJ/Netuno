package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.servicos.NavioService;

@ManagedBean(name="navioBean")
@ViewScoped
public class NavioBean extends MBean {
	
	@EJB
	private NavioService servicosNavio;
	
	private Navio navio;
	private List<Navio> navios;
	private Integer idNavio;
	private List<Carga> cargasNavio;
	
	@PostConstruct
	public void inicializar(){
		this.navios = servicosNavio.filtrar((new Navio()));
		if(this.navio==null){
			this.navio = new Navio();
		}
	}
	
	public String salvar(){
		try{
			if(navio.getId()!=null) this.servicosNavio.alterar(navio);
			else this.servicosNavio.salvar(navio);
			this.navios = servicosNavio.filtrar(new Navio());
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Navio salvo com sucesso", null);
		} catch (Exception e){
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getMessage() , null);
			return null;
		}
		return "listar";
	}
	
	public void recuperarNavio(){
		this.navio = servicosNavio.obterPorId(idNavio);
	}
	
	public String excluir(){
		try{
			servicosNavio.excluir(idNavio);
		} catch (Exception e){
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getMessage() , null);
			return null;
		}
		return "listar";
	}
	
	public void consultar(){
		this.navios = servicosNavio.filtrar(navio);
	}

	public NavioService getServicosNavio() {
		return servicosNavio;
	}

	public void setServicosNavio(NavioService servicosNavio) {
		this.servicosNavio = servicosNavio;
	}

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public List<Navio> getNavios() {
		return navios;
	}

	public void setNavios(List<Navio> navios) {
		this.navios = navios;
	}

	public Integer getIdNavio() {
		return idNavio;
	}

	public void setIdNavio(Integer idNavio) {
		this.idNavio = idNavio;
	}

	public List<Carga> getCargasNavio() {
		return cargasNavio;
	}

	public void setCargasNavio(List<Carga> cargasNavio) {
		this.cargasNavio = cargasNavio;
	}
}
