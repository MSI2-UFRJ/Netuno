package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@Stateless
public class PregaoServiceImpl implements PregaoService{

	private static final long serialVersionUID = -2921606759603223224L;

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Pregao> recuperaPregoesAbertos() {
		Query query = em.createNamedQuery("Pregao.recuperaPregoesAtivos");
		return (List<Pregao>) query.getResultList();
	}
	
	public EntityManager getEm() {
		return em;
	}

	@SuppressWarnings("unchecked")
	public List<Carga> recuperaCargasSemPregao() {
		Query query = em.createNamedQuery("Carga.recuperaCargasSemPregao");
		return (List<Carga>) query.getResultList();
	}

	@Override
	public void salvarPregao(Pregao p) {
		em.persist(p);
		
	}

}
