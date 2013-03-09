package br.com.ufrj.msi2.netuno.modelo.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;

@Stateless
public class ContratanteServiceImpl implements ContratanteService {
	private static final long serialVersionUID = -7087952795393582189L;

	@PersistenceContext
	EntityManager em;
	
	public void salvarContratante(Contratante contratante) {
		em.persist(contratante);
	}
	
	public Contratante recuperaPorId(Integer id) {
		Query query = em.createNamedQuery("Contratante.recuperaPorIdComFetch");
		query.setParameter("id", id);
		return (Contratante) query.getSingleResult();
	}

	public EntityManager getEm() {
		return em;
	}

}
