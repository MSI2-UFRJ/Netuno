package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@Stateless
public class PregaoServiceImpl implements PregaoService{

	private static final long serialVersionUID = -2921606759603223224L;

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Pregao> recuperaPregoesAbertos() {
		Query query = em.createQuery("SELECT * FROM pregao");
		//query.setParameter(?);
		return (List<Pregao>) query.getResultList();
	}
	
	public EntityManager getEm() {
		return em;
	}

}
