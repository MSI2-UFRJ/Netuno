package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Atraque;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.AtraqueService;
import br.com.ufrj.msi2.netuno.modelo.servicos.NavioService;
import br.com.ufrj.msi2.netuno.modelo.servicos.PortoService;

@ManagedBean(name="atraqueBean")
@ViewScoped
public class AtraqueBean extends MBean {

	@EJB
	AtraqueService servicosAtraque;
	@EJB
	PortoService servicosPorto;
	@EJB
	NavioService servicosNavio;
	
	private Atraque atraque;
	private List<Atraque> atraques;
	private Integer idAtraque;
	private Navio navio;
	private Integer idNavio;
	private Porto porto;
	private Integer idPorto;
	private List<Porto> portos;
	private List<Navio> navios;
	
	@PostConstruct
	public void inicializar(){
		portos = servicosPorto.obterTodos();
		navios = servicosNavio.filtrar(new Navio());
		if(atraque==null){
			atraque = new Atraque();
		}
	}
	
	public void recuperarNavio(){
		navio = servicosNavio.obterPorId(idNavio);
	}
	
	public void recuperarPorto(){
		porto = servicosPorto.obterPorId(idPorto);
	}
	
	public String salvar(){
		String outcome = null;
		if(navio!=null){
			atraque.setNavio(navio);
			outcome="/navios/";
		}
		if(porto!=null){
			atraque.setPorto(porto);
			outcome="/portos/";
		}
		try {
			servicosAtraque.incluir(this.atraque);
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Atraque agendado com sucesso.", null);
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
		return outcome+"listar";
	}
	
	public void excluir(){
		try {
			servicosAtraque.excluir(idAtraque);
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Agendamento de atraque excluído com sucesso.", null);
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
	}
	
	public Atraque getAtraque() {
		return atraque;
	}
	
	public void setAtraque(Atraque atraque) {
		this.atraque = atraque;
	}
	
	public List<Atraque> getAtraques() {
		return atraques;
	}
	
	public void setAtraques(List<Atraque> atraques) {
		this.atraques = atraques;
	}
	
	public Integer getIdAtraque() {
		return idAtraque;
	}
	
	public void setIdAtraque(Integer idAtraque) {
		this.idAtraque = idAtraque;
	}
	
	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public Integer getIdNavio() {
		return idNavio;
	}

	public void setIdNavio(Integer idNavio) {
		this.idNavio = idNavio;
	}

	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}
	
	public Integer getIdPorto(){
		return idPorto;
	}
	
	public void setIdPorto(Integer idPorto){
		this.idPorto = idPorto;
	}

	public List<Porto> getPortos() {
		return portos;
	}
	
	public void setPortos(List<Porto> portos) {
		this.portos = portos;
	}

	public List<Navio> getNavios() {
		return navios;
	}

	public void setNavios(List<Navio> navios) {
		this.navios = navios;
	}
	
	
}
