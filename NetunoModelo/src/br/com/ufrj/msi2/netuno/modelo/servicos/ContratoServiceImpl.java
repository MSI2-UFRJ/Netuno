package br.com.ufrj.msi2.netuno.modelo.servicos;

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
	
	@SuppressWarnings("unchecked")
	public List<Contrato> recuperaContratosPorContratante(Contratante contratante) {
		Query query = em.createNamedQuery("Contrato.recuperaPorContratante");
		
		query.setParameter("contratante", contratante);
		
		return (List<Contrato>) query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

}
