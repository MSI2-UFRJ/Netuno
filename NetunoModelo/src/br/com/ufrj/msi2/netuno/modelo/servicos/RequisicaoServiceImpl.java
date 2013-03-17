package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@Stateless
public class RequisicaoServiceImpl implements RequisicaoService {
	
	private static final long serialVersionUID = -8723029717925736050L;

	@PersistenceContext
	EntityManager em;
	
	
	public EntityManager getEm() {
		return em;
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return this.em.getCriteriaBuilder();
	}

	@Override
	public Requisicao obterPorId(Integer idRequisicao) {
		Query query = em.createNamedQuery("Requisicao.obterPorId");
		query.setParameter("id", idRequisicao);
		return (Requisicao) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Requisicao> obterPorAgenteCarga(AgenteCarga agente) {
		Query query = em.createNamedQuery("Requisicao.obterPorAgenteCarga");
		query.setParameter("agente", agente);
		return (List<Requisicao>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Requisicao> recuperarTodos() {
		Query query = em.createNamedQuery("Requisicao.obterTodos");
		return (List<Requisicao>) query.getResultList();
	}

	@Override
	public List<Requisicao> filtrar(CriteriaQuery<Requisicao> consulta) {
		//TODO: Implement
		throw new NotImplementedException();
	}

	@Override
	public Requisicao criarRequisicao() {
		//TODO: Implement		
		throw new NotImplementedException();
	}

	@Override
	public void salvarRequisicao(Requisicao requisicao) {
		//TODO: Implement
		throw new NotImplementedException();
		
	}

	@Override
	public void atenderResquisicao(Requisicao requisicao) {
		requisicao.setRequisicaoAtendida(true);
		em.merge(requisicao);
		
	}


}
