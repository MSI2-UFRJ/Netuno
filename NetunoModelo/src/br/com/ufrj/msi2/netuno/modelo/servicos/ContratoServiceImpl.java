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

	public Contrato recuperaContratoPorId(Integer id) {
		Query query = em.createNamedQuery("Contrato.recuperaPorId");
		query.setParameter("id", id);
		return (Contrato) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	private List<Contrato> recuperaContratosPorContratante(Contratante contratante, SituacaoContratoEnum situacao) {
		Query query = em.createNamedQuery("Contrato.recuperaPorContratante");
		query.setParameter("contratante", contratante);
		query.setParameter("situacao", situacao.name());
		return (List<Contrato>) query.getResultList();
	}

	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante) {
		return this.recuperaContratosPorContratante(contratante, SituacaoContratoEnum.aberto);
	}

	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante) {
		return this.recuperaContratosPorContratante(contratante, SituacaoContratoEnum.finalizado);
	}

	public EntityManager getEm() {
		return em;
	}

}
