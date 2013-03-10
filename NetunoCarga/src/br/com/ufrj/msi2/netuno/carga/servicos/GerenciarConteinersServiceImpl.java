package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaService;
import br.com.ufrj.msi2.netuno.modelo.servicos.ConteinerService;

@Stateless
public class GerenciarConteinersServiceImpl implements GerenciarConteinersService {

	private static final long serialVersionUID = -1640096335992798113L;
	@EJB
	ConteinerService service;

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
//			if(carga.getContrato().getPortoDestino()!=null && !carga.getContrato().getPortoDestino().equals("")) {
//				Expression<String> exp = root.get("portoDestino");
//				predicados.add(
//						builder.or(
//								builder.equal(builder.lower(exp),carga.getContrato().getPortoDestino().getId()),
//								builder.equal(builder.lower(exp),"")
//								)
//							);
//			}
//			else
//			{
//				Expression<String> exp = root.get("portoDestino");
//				predicados.add(builder.equal(builder.lower(exp),""));
//			}
			
			criteria.select(root).where(predicados.toArray(new Predicate[]{}));
			
			resultList = service.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
}
