package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.enums.SituacaoContratoEnum;

@Stateless
public class ContratoServiceImpl implements ContratoService {
	private static final long serialVersionUID = -7087952795393582189L;

	@PersistenceContext
	EntityManager em;
	
	public Contrato criarContrato() {
		Contrato contrato = new Contrato();
		
		contrato.setSituacao(SituacaoContratoEnum.aberto);

		return contrato;
	}

	public void salvarContrato(Contrato contrato) {
		em.persist(contrato);
	}
	
	public void salvarContratoAguardandoColeta(Contrato contrato) {
		contrato.setSituacao(SituacaoContratoEnum.aguardandoColeta);
		this.salvarContrato(contrato);
	}

	public Contrato recuperaContratoPorId(Integer id) {
		Query query = em.createNamedQuery("Contrato.recuperaPorId");
		query.setParameter("id", id);
		return (Contrato) query.getSingleResult();
	}
	
	public Contrato recuperaContratoComCargas(Contrato contrato) {
		Query query = em.createNamedQuery("Contrato.recuperaPorIdComFetch");
		query.setParameter("id", contrato.getId());
		return (Contrato) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante) {
		Query query = em.createNamedQuery("Contrato.recuperaAbertoPorContratante");
		query.setParameter("contratante", contratante);
		query.setParameter("situacao", SituacaoContratoEnum.aguardandoEntrega.ordinal());
		return (List<Contrato>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante) {
		Query query = em.createNamedQuery("Contrato.recuperaFinalizadoPorContratante");
		query.setParameter("contratante", contratante);
		query.setParameter("situacao", SituacaoContratoEnum.finalizado.ordinal());
		return (List<Contrato>) query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

}
