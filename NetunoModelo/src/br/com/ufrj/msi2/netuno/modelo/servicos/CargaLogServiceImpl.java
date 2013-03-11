package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;

@Stateless
public class CargaLogServiceImpl implements CargaLogService {

	private static final long serialVersionUID = -5102370475324194502L;
	
	@PersistenceContext
	EntityManager em;
	
	public EntityManager getEm() {
		return em;
	}
	
	@Override
	public CargaLog recuperaUltimoCargaLogDeCarga(Carga carga) {
		Query query = em.createNamedQuery("CargaLog.recuperaUltimaCargaLogDeCarga");
		query.setParameter("carga", carga);
		query.setMaxResults(1);
		
		@SuppressWarnings("unchecked")
		List<CargaLog> lista = query.getResultList();
		
		if(lista == null || lista.size() == 0) {
			return null;
		}
		
		return lista.get(0);
	}
	
}