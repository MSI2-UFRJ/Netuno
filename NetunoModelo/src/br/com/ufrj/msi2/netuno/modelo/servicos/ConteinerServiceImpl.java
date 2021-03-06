package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

/**
 * Session Bean implementation class ConteinerServiceImpl
 */
@Stateless
public class ConteinerServiceImpl implements ConteinerService {
	private static final long serialVersionUID = -2476819130385260418L;
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}

	@Override
	public List<Conteiner> filtrar(CriteriaQuery<Conteiner> consulta) {
		List<Conteiner> list = em.createQuery(consulta).getResultList();
		return list;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return this.em.getCriteriaBuilder();
	}

	@Override
	public void salvarConteiner(Conteiner conteiner) {
		em.persist(conteiner);
		
	}

	@Override
	public Conteiner obterPorId(Integer idConteiner) {
		return this.em.find(Conteiner.class, idConteiner);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conteiner> recuperarTodos() {
		Query query = em.createNamedQuery("Conteiner.recuperarTodos");
		return (List<Conteiner>) query.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Conteiner> recuperarConteinersPorNavio(Navio navio) {
		Query query = em.createNamedQuery("Conteiner.recuperarConteinersPorNavio");
		query.setParameter("navio", navio);
		return (List<Conteiner>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Conteiner> recuperarPorNavioPorAgenteParaDesembarque(Navio navio, AgenteCarga agente) {
		Query query = em.createNamedQuery("Conteiner.recuperarPorNavioPorAgenteParaDesembarque");
		query.setParameter("navio", navio);
		query.setParameter("porto", agente.getPertence());
		query.setParameter("agente", agente);
		return (List<Conteiner>) query.getResultList();
	}

	@Override
	public void atualizarConteiner(Conteiner conteiner) {
		em.merge(conteiner);
	}
}
