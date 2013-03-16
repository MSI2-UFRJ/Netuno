package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.Atraque;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Stateless
public class AtraqueServiceImpl implements AtraqueService {
	private static final long serialVersionUID = -4048074913764741373L;

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Atraque obterPorId(Integer idAtraque){
		if(idAtraque==null) return null;
		else return em.find(Atraque.class, idAtraque);
	}
	
	@Override
	public List<Atraque> obterAtraquesPorto(Integer idPorto) {
		Atraque atraque = new Atraque();
		Porto porto = new Porto();
		porto.setId(idPorto);
		atraque.setPorto(porto);
		return this.filtrar(atraque);
	}

	@Override
	public List<Atraque> obterAtraquesNavio(Integer idNavio) {
		Atraque atraque = new Atraque();
		Navio navio = new Navio();
		navio.setId(idNavio);
		atraque.setNavio(navio);
		return this.filtrar(atraque);
	}

	@Override
	public void incluir(Atraque atraque) throws Exception {
		try{
			this.em.persist(atraque);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao agendar o atraque.", e.getCause());
		}
	}

	@Override
	public void alterar(Atraque atraque) throws Exception {
		try{
			this.em.merge(atraque);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao alterar o atraque.", e.getCause());
		}
	}

	@Override
	public void excluir(Integer idAtraque) throws Exception {
		try{
			Atraque atraque = this.obterPorId(idAtraque);
			em.remove(atraque);
		} catch(Exception e){
			throw new Exception("Não foi possível excluir o atraque selecionado.", e.getCause());
		}
	}

	@Override
	public List<Atraque> filtrar(Atraque atraque) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Atraque> consulta = cb.createQuery(Atraque.class);
		Root<Atraque> atraqueRoot = consulta.from(Atraque.class);
		
		ArrayList<Predicate> predicados = new ArrayList<Predicate>();
		if(atraque!=null){
			//Cada atributo setado em porto é um filtro e portanto é colocado numa lista de predicados
			if(atraque.getPorto()!=null && atraque.getPorto().getId()!=null){
				predicados.add(cb.equal(atraqueRoot.get("porto"), atraque.getPorto().getId()));
			} 
			if(atraque.getNavio()!=null && atraque.getNavio().getId()!=null){
				predicados.add(cb.equal(atraqueRoot.get("navio"), atraque.getNavio().getId()));
			} 
		}
		consulta.select(atraqueRoot).where(predicados.toArray(new Predicate[]{}));
		consulta.orderBy(cb.asc((atraqueRoot.get("data_previsao_chegada"))));
		return this.em.createQuery(consulta).getResultList();
	}
}
