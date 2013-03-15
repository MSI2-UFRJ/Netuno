package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Stateless
public class PortoServiceImpl implements PortoService{
	private static final long serialVersionUID = -6048886861529586683L;
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Porto> obterTodos(){
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Porto> criteria = builder.createQuery(Porto.class);
		Root<Porto> porto = criteria.from(Porto.class);
		criteria.select(porto);
		criteria.orderBy(builder.asc((porto.get("localizacao"))));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Porto obterPorId(Integer idPorto){
		if(idPorto!=null) return this.em.find(Porto.class, idPorto);
		else return null;
	}

	/**
	 * Inclui um novo registro de Porto com os atributos do parâmetro porto.
	 * @param porto uma instância de {@link Porto} cujos atributos contém os valores que serão salvos.
	 */
	@Override
	public void salvar(Porto porto) throws Exception {
		Porto filtroPorto = new Porto();
		filtroPorto.setLocalizacao(porto.getLocalizacao());
		filtroPorto.setNome(porto.getNome());
		List<Porto> valida = filtrar(filtroPorto);
		if (valida!=null && !valida.isEmpty()) throw new Exception("Já existe um porto com o mesmo nome e localização");
		try{
			this.em.persist(porto);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao incluir o porto.", e.getCause());
		}
	}

	/**
	 * Altera um registro de Porto com os atributos do parâmetro porto.
	 * @param porto a instância de {@link Porto} cujos atributos contém os valores que serão alterados.
	 */
	@Override
	public void alterar(Porto porto) throws Exception {
		Porto filtroPorto = new Porto();
		filtroPorto.setLocalizacao(porto.getLocalizacao());
		filtroPorto.setNome(porto.getNome());
		List<Porto> valida = filtrar(filtroPorto);
		if (valida!=null && !valida.isEmpty() && !porto.getId().equals(valida.get(0).getId()))
			throw new Exception("Já existe um porto com o mesmo nome e localização");
		try{
			this.em.merge(porto);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao alterar o porto.", e.getCause());
		}
	}
	
	@Override
	public void excluir(Integer idPorto) throws Exception{
		try{
			Porto porto = this.em.find(Porto.class, idPorto);
			this.em.remove(porto);
		} catch (Exception e){
			throw new Exception("Não foi possível excluir o porto selecionado.", e.getCause());
		}
	}

	/**
	 * Faz uma consulta ao banco pelos portos com os atributos iguais aos setados no parâmetro porto.
	 * @param porto uma instância de {@link Porto} com os atributos da consulta.
	 */
	@Override
	public List<Porto> filtrar(Porto porto){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Porto> consulta = cb.createQuery(Porto.class);
		Root<Porto> portoRoot = consulta.from(Porto.class);
		
		ArrayList<Predicate> predicados = new ArrayList<Predicate>();
		if(porto!=null){
			//Cada atributo setado em porto é um filtro e portanto é colocado numa lista de predicados
			if(porto.getLocalizacao()!=null && !porto.getLocalizacao().equals("")){
				Expression<String> localizacao = portoRoot.get("localizacao");
				predicados.add(cb.like(cb.lower(localizacao), porto.getLocalizacao().toLowerCase()+"%"));
			} 
			if(porto.getNome()!=null && !porto.getNome().equals("")){
				Expression<String> nome = portoRoot.get("nome");
				predicados.add(cb.like(cb.lower(nome), porto.getNome().toLowerCase()+"%"));
			}
		}
		//Executa a consulta com Porto como entidade raiz e satisfazendo as condições dos predicados
		consulta.select(portoRoot).where(predicados.toArray(new Predicate[]{}));
		consulta.orderBy(cb.asc((portoRoot.get("localizacao"))));
		return this.em.createQuery(consulta).getResultList();
	}

}
