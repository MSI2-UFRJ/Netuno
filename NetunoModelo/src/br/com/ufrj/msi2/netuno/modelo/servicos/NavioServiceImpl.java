package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;


@Stateless
public class NavioServiceImpl implements NavioService {

	private static final long serialVersionUID = 260372791711386635L;

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
	public List<Navio> filtrar(CriteriaQuery<Navio> consulta) {
		List<Navio> list = em.createQuery(consulta).getResultList();
		return list;
	}
	
	public void salvar(Navio navio) {
		em.persist(navio);
	}
	
	@Override
	public Navio obterPorId(Integer idNavio){
		return this.em.find(Navio.class, idNavio);
	}
	
	@SuppressWarnings("unchecked")
	public List<Navio> recuperaTodos() {
		Query query = em.createNamedQuery("Navio.recuperarTodos");
		return (List<Navio>) query.getResultList();
	}
}