package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@Stateless
public class ContratoServiceImpl implements ContratoService {
	private static final long serialVersionUID = -7087952795393582189L;

	@PersistenceContext
	EntityManager em;
	
	public void salvarContrato(Contrato contrato) {
		em.persist(contrato);
	}
	
	public Contrato recuperaContratoPorId(Integer id) {
		Query query = em.createNamedQuery("Contrato.recuperaPorId");
		query.setParameter("id", id);
		return (Contrato) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante) {
		Query query = em.createNamedQuery("Contrato.recuperaAbertosPorContratante");
		query.setParameter("contratante", contratante);
		return (List<Contrato>) query.getResultList();
	}
	
	//@SuppressWarnings("unchecked")
	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante) {
		return new ArrayList<Contrato>();
	}

	public EntityManager getEm() {
		return em;
	}

}
