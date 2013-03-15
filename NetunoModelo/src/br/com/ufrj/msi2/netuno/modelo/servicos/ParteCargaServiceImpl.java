package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;


@Stateless
public class ParteCargaServiceImpl implements ParteCargaService {

	private static final long serialVersionUID = -5102370475324194502L;
	
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
	public List<ParteCarga> filtrar(CriteriaQuery<ParteCarga> consulta) {
		List<ParteCarga> list = em.createQuery(consulta).getResultList();
		return list;
	}
	
	public void salvar(ParteCarga pCarga) {
		em.persist(pCarga);
	}
	
	@Override
	public ParteCarga obterPorId(Integer id){
		return this.em.find(ParteCarga.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ParteCarga> listaParteCargasDisponiveis(AgenteCarga agente) {
		Query query = em.createNamedQuery("ParteCarga.listaParteCargasParaDesembarque");
		query.setParameter("porto", agente.getPertence().getId());
		query.setParameter("agente", agente.getId());
		return query.getResultList();

		
	}
}