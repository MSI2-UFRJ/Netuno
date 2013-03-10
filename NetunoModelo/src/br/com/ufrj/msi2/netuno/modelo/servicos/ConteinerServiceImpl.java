package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;

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
}
