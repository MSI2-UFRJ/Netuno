package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.ConteinerService;

@Stateless
public class GerenciarConteinersServiceImpl implements GerenciarConteinersService {

	private static final long serialVersionUID = -1640096335992798113L;
	
	@EJB
	ConteinerService service;
	
	public ConteinerService getConteinerService() {
		return service;
	}
	@Override
	public List<Conteiner> listaConteinersDisponiveis(Carga carga,Porto porto)
	{
		List<Conteiner> resultList = new ArrayList<Conteiner>();

		try {
			CriteriaBuilder builder = service.getCriteriaBuilder();
			CriteriaQuery<Conteiner> criteria = builder.createQuery(Conteiner.class);
			Root<Conteiner> root = criteria.from(Conteiner.class);
			
			ArrayList<Predicate> predicados = new ArrayList<Predicate>();
			Expression<String> exp = root.get("porto");
			predicados.add(builder.equal(builder.lower(exp),porto.getId()));
			
			Expression<String> expQt = root.get("pesoDisponivel");
			predicados.add(builder.notEqual(builder.lower(expQt),"0"));
			
			criteria.select(root).where(predicados.toArray(new Predicate[]{}));
			
			resultList = service.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
	
	@Override
	public Conteiner obterPorId(Integer id)
	{
		if(id!=null && id > 0) return this.service.obterPorId(id);
		else return null;
	}
	
	@Override
	public void criarNovoConteiner(Porto porto){
		Conteiner novo = new Conteiner();
		novo.setPorto(porto);
		novo.setPesoDisponivel((double)Conteiner.getPesomaximo());
		service.salvarConteiner(novo);
	}
	@Override
	public void AtualizaPeso(Conteiner conteiner, double novoPeso)
	{
		conteiner = service.obterPorId(conteiner.getId());
		conteiner.setPesoDisponivel(novoPeso < 0? 0 : novoPeso);
		
		service.salvarConteiner(conteiner);
	}
	@Override
	public List<Conteiner> recuperarTodos() {
		return service.recuperarTodos();
	}
	@Override
	public List<Conteiner> recuperarConteinersPorNavio(Navio navio) {
		return service.recuperarConteinersPorNavio(navio);
	}
	
	public List<Conteiner> recuperarPorNavioPorAgenteParaDesembarque(Navio navio, AgenteCarga agente){
		return service.recuperarPorNavioPorAgenteParaDesembarque(navio, agente);
	}
}
