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
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.ConteinerService;

@Stateless
public class GerenciarConteinersServiceImpl implements GerenciarConteinersService {

	private static final long serialVersionUID = -1640096335992798113L;
	@EJB
	ConteinerService service;
	@EJB
	
	
	
	public ConteinerService getConteinerService() {
		return service;
	}
	
	public List<Conteiner> listaConteinersDisponiveis(Carga carga,Porto porto)
	{
		List<Conteiner> resultList = new ArrayList<Conteiner>();

		try {
			CriteriaBuilder builder = service.getCriteriaBuilder();
			CriteriaQuery<Conteiner> criteria = builder.createQuery(Conteiner.class);
			Root<Conteiner> root = criteria.from(Conteiner.class);
			
			ArrayList<Predicate> predicados = new ArrayList<Predicate>();
			if(porto.getLocalizacao()!= null && !porto.getLocalizacao().equals("")){
				Expression<String> exp = root.get("portoOrigem");
				predicados.add(builder.equal(builder.lower(exp),porto.getId()));
			} 
			
			criteria.select(root).where(predicados.toArray(new Predicate[]{}));
			
			resultList = service.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
	
	public void criarNovoConteiner(Porto porto){
		Conteiner novo = new Conteiner();
		novo.setPortoOrigem(porto);
		novo.setPesoDisponivel((double)Conteiner.getPesomaximo());
		service.salvarConteiner(novo);
	}
}
