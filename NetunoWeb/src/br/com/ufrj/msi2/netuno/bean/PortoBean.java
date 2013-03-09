package br.com.ufrj.msi2.netuno.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	public PortoService getServicosPorto() {
		return servicosPorto;
	}

	public void setServicosPorto(PortoService servicosPorto) {
		this.servicosPorto = servicosPorto;
	}

	public void recuperarPorto(){
		this.porto = servicosPorto.obterPorId(this.idPorto);
	}
	
	/**
	 * Inclui um novo registro de porto com os atributos do parâmetro porto.
	 * @return próxima página para a qual o usuário será mandado na navegação.
	 */
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

	public void excluir(){
		try{
			if(idPorto!=null){
				this.servicosPorto.excluir(idPorto);
				super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto excluído com sucesso", "Porto excluído com sucesso");
			}
		
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getCause().getMessage() , e.getCause().getMessage());
		}
		portos = servicosPorto.obterTodos();
	}
	
	/**
	 * Atualiza uma instância de porto já cadastrada com os atributos do parâmetro porto .
	 * <br>-Não é possível salvar um porto com uma localização que já esteja cadastrada para outro
	 * 		porto.
	 * @return próxima página para a qual o usuário será mandado na navegação.
	 */
	public String alterar(){
		try{
			this.servicosPorto.alterar(porto);
			super.sendMessage(null, FacesMessage.SEVERITY_INFO, "Porto alterado com sucesso", "Porto alterado com sucesso");
		
		} catch (Exception e) {
			super.sendMessage(null, FacesMessage.SEVERITY_ERROR, e.getCause().getMessage() , e.getCause().getMessage());
			return null;
		}
		portos = servicosPorto.obterTodos();
		return "listar";
	}
	
	/**
	 * Retorna os resultados de uma consulta feita utilizando os parâmetros do atributo porto.
	 * Envia uma criteria para ser processada pelo serviço de Porto.
	 * <br>-Para o parâmetro 'localização' são retornados portos cuja localização começe com o
	 * 		valor passado.
	 */
	public void consultar(){
		CriteriaBuilder cb = servicosPorto.getCriteriaBuilder();
		CriteriaQuery<Porto> consulta = cb.createQuery(Porto.class);
		Root<Porto> porto = consulta.from(Porto.class);
		ArrayList<Predicate> predicados = new ArrayList<Predicate>();
		if(this.porto.getLocalizacao()!=null && !this.porto.getLocalizacao().equals("")){
			Expression<String> localizacao = porto.get("localizacao");
			predicados.add(cb.like(cb.lower(localizacao) ,this.porto.getLocalizacao().toLowerCase()+"%"));
		} 
		if(this.porto.getNome()!=null && !this.getPorto().equals("")){
			Expression<String> nome = porto.get("nome");
			predicados.add(cb.like(cb.lower(nome) ,this.porto.getNome().toLowerCase()+"%"));
		}
		consulta.select(porto).where(predicados.toArray(new Predicate[]{}));
		this.portos = servicosPorto.filtrar(consulta);
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
