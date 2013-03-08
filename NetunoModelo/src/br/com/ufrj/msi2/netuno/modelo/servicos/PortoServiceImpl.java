package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Stateless
public class PortoServiceImpl implements PortoService{
	private static final long serialVersionUID = -6048886861529586683L;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Porto> obterTodos() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Porto> criteria = builder.createQuery(Porto.class);
		Root<Porto> porto = criteria.from(Porto.class);
		criteria.select(porto);
		criteria.orderBy(builder.asc((porto.get("localizacao"))));
		return em.createQuery(criteria).getResultList();
	}

	@Override
	public Porto obterPorId(Integer idPorto) {
		return this.em.find(Porto.class, idPorto);
	}

	@Override
	public void salvar(Porto porto) {
		this.em.persist(porto);
		
	}

	@Override
	public void excluir(Integer idPorto) {
		try{
			Porto porto = this.em.find(Porto.class, idPorto);
			this.em.remove(porto);
		} catch (Exception e){
			
		}
	}

	@Override
	public void alterar(Porto porto) {
		this.em.merge(porto);	
	}

	@Override
	public List<Porto> filtrar(CriteriaQuery<Porto> consulta) {
		return this.em.createQuery(consulta).getResultList();
		
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return this.em.getCriteriaBuilder();
	}

}
