package br.com.ufrj.msi2.netuno.modelo.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;

@Stateless
public class ContratanteServiceImpl implements ContratanteService {
	private static final long serialVersionUID = -7087952795393582189L;

	@PersistenceContext
	EntityManager em;
	
	public Contratante criarContratante() {
		return new Contratante();
	}
	
	public void salvarContratante(Contratante contratante) {
		em.persist(contratante);
	}
	
	public Contratante recuperaPorId(Integer id) {
		Query query = em.createNamedQuery("Contratante.recuperaPorIdComFetch");
		query.setParameter("id", id);
		return (Contratante) query.getSingleResult();
	}
	
	@Override
	public Contratante recuperaPorCPF(CPF cpf) throws ResultadoNaoEncontradoException {
		Query query = em.createNamedQuery("Contratante.recuperaPorCPF");
		query.setParameter("cpf", cpf.getCpf());
		
		try {
			return (Contratante) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResultadoNaoEncontradoException(e.getMessage());
		}
	}

	public EntityManager getEm() {
		return em;
	}

}
