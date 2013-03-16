package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;


@Stateless
public class NavioServiceImpl implements NavioService {

	private static final long serialVersionUID = 260372791711386635L;

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
	public List<Navio> filtrar(CriteriaQuery<Navio> consulta) {
		List<Navio> list = em.createQuery(consulta).getResultList();
		return list;
	}
	
	public void salvar(Navio navio) throws Exception {
		Navio filtroNavio = new Navio();
		filtroNavio.setNome(navio.getNome());
		List<Navio> valida = filtrar(filtroNavio);
		if(valida!=null && !valida.isEmpty())
			throw new Exception("Já existe um navio com o mesmo nome.");
		try{
			em.persist(navio);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao incluir o navio", e.getCause());
		}
	}
	
	@Override
	public Navio obterPorId(Integer idNavio){
		return this.em.find(Navio.class, idNavio);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Navio> recuperaTodos() {
		Query query = em.createNamedQuery("Navio.recuperarTodos");
		return (List<Navio>) query.getResultList();
	}
	
	@Override
	public void alterar(Navio navio) throws Exception{
		Navio filtroNavio = new Navio();
		filtroNavio.setNome(navio.getNome());
		List<Navio> valida = filtrar(filtroNavio);
		if(valida!=null && !valida.isEmpty() && !navio.getId().equals(valida.get(0).getId()))
			throw new Exception("Já existe um navio com o mesmo nome.");
		try{
			em.merge(navio);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao alterar o navio", e.getCause());
		}
	}

	@Override
	public void excluir(Integer idNavio) throws Exception {
		try{
			Navio navio = this.em.find(Navio.class, idNavio);
			this.em.remove(navio);
		} catch (Exception e){
			throw new Exception("Ocorreu um erro ao excluir o navio selecionado.", e.getCause());
		}
	}

	@Override
	public List<Navio> filtrar(Navio navio) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Navio> consulta = cb.createQuery(Navio.class);
		Root<Navio> navioRoot = consulta.from(Navio.class);
		ArrayList<Predicate> predicados = new ArrayList<Predicate>();
		if(navio!=null){
			if(navio.getNome()!=null && !navio.getNome().equals("")){
				Expression<String> nome = navioRoot.get("nome");
				predicados.add(cb.like(cb.lower(nome), navio.getNome().toLowerCase()+"%"));
			}
		}
		consulta.select(navioRoot).where(predicados.toArray(new Predicate[]{}));
		consulta.orderBy(cb.asc((navioRoot.get("nome"))));
		return em.createQuery(consulta).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Navio> recuperarNaviosAtracadosEmPorto(Porto porto) {
		Query query = em.createNamedQuery("Navio.recuperarNaviosAtracadosEmPorto");
		query.setParameter("porto", porto);		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Navio> recuperarNaviosPorAgenteAtracadosEmPorto(AgenteCarga agente) {
		Query query = em.createNamedQuery("Navio.recuperarNaviosPorAgenteAtracadosEmPorto");
		query.setParameter("porto", agente.getPertence());
		query.setParameter("agente", agente);
		return query.getResultList();
	}
}