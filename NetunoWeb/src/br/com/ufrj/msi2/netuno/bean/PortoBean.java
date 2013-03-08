package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.PortoService;

@ManagedBean(name="portoBean")
@ViewScoped
public class PortoBean extends MBean{

	@EJB
	private PortoService servicosPorto;
	
	private Porto porto = new Porto();
	private List<Porto> portos;
	private Integer idPorto;
	
	@PostConstruct
	public void inicializar() {
		this.portos = this.servicosPorto.obterTodos();
		if (this.porto == null){
			this.porto = new Porto();
		}
	}
	
	public void recuperarPorto(){
		this.porto = servicosPorto.obterPorId(this.idPorto);
	}
	
	public String salvar(){
		try{
			this.servicosPorto.salvar(porto);
			this.portos = this.servicosPorto.obterTodos();
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto salvo com sucesso", "Porto salvo com sucesso");
		} catch (Exception e){
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getCause().getMessage() , e.getCause().getMessage());
			return null;
		}
		return "listar";
	}

	public String excluir(){
		try{
			if(idPorto!=null){
				this.servicosPorto.excluir(idPorto);
				super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto excluído com sucesso", "Porto excluído com sucesso");
			}
		
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getCause().getMessage() , e.getCause().getMessage());
		}
		portos = servicosPorto.obterTodos();
		return "listar";
	}
	
	public String alterar(){
		try{
			this.servicosPorto.alterar(porto);
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto alterado com sucesso", "Porto alterado com sucesso");
		
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getCause().getMessage() , e.getCause().getMessage());
		}
		portos = servicosPorto.obterTodos();
		return "listar";
	}
	
	public String consultar(String localizacao){
		if(localizacao!=null && !localizacao.equals("")){
			CriteriaBuilder cb = servicosPorto.getCriteriaBuilder();
			CriteriaQuery<Porto> consulta = cb.createQuery(Porto.class);
			Root<Porto> porto = consulta.from(Porto.class);
			consulta.where(cb.equal(porto.get("localizacao"),localizacao));
			portos = servicosPorto.filtrar(consulta);
		} else {
			portos = servicosPorto.obterTodos();
		} return "listar";
	}
	
	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}

	public List<Porto> getPortos() {
		return portos;
	}

	public void setPortos(List<Porto> portos) {
		this.portos = portos;
	}
	
	public void setIdPorto(Integer idPorto){
		this.idPorto = idPorto;
	}
	
	public Integer getIdPorto(){
		return this.idPorto;
	}
}
