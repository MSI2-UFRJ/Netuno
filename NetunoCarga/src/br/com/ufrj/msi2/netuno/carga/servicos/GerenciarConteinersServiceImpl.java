package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaService;

@Stateless
public class GerenciarConteinersServiceImpl implements GerenciarConteinersService {

	private static final long serialVersionUID = -1640096335992798113L;
	@EJB
	CargaService cargaService;

	public CargaService getCargaService() {
		return cargaService;
	}
	
	public List<Conteiner> listaConteinersDisponiveis(Porto porto)
	{
		List<Conteiner> resultList = new ArrayList<Conteiner>();

//		try {
//
//			CriteriaBuilder builder = cargaService.getCriteriaBuilder();
//			CriteriaQuery<Carga> criteria = builder.createQuery(Carga.class);
//			Root<Carga> cargaRoot = criteria.from(Carga.class);
//			
//			Expression<String> agenteEmbarque = cargaRoot.get("agenteEmbarque");
//			criteria.select(cargaRoot).where(builder.equal(builder.lower(agenteEmbarque),agente.getId()));
//			
//			resultList = cargaService.filtrar(criteria);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		return resultList;
	}

}
