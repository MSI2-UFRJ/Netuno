package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;

@Stateless
public class CargaServiceImpl implements CargaService {

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
	public List<Carga> filtrar(CriteriaQuery<Carga> consulta) {
		List<Carga> list = em.createQuery(consulta).getResultList();
		return list;
	}
	
	public void salvar(Carga carga) {
		em.persist(carga);
	}
	
	@Override
	public Carga obterPorId(Integer idCarga){
		return this.em.find(Carga.class, idCarga);
	}
}