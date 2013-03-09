package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.PortoService;

@ManagedBean(name="portoBean")
@ViewScoped
public class PortoBean extends MBean{

	@EJB
	private PortoService servicosPorto;
	
	private Porto porto;
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
	
	/**
	 * Se o id do porto a ser salvo for nulo trata-se de uma inclusão. Caso contrário é uma alteração.
	 */
	public String salvar(){
		try{
			if(porto.getId()!=null) this.servicosPorto.alterar(porto);
			else this.servicosPorto.salvar(porto);
			this.portos = this.servicosPorto.obterTodos();
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto salvo com sucesso", null);
		} catch (Exception e){
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getMessage() , null);
			return null;
		}
		return "listar";
	}

	public void excluir(){
		try{
			if(idPorto!=null){
				this.servicosPorto.excluir(idPorto);
				portos = servicosPorto.obterTodos();
				super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto excluído com sucesso", null);
			}
		
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
		}
	}
	
	public void consultar(){
		this.portos = servicosPorto.filtrar(this.porto);
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
	
	public PortoService getServicosPorto() {
		return servicosPorto;
	}

	public void setServicosPorto(PortoService servicosPorto) {
		this.servicosPorto = servicosPorto;
	}

}
