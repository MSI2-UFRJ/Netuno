package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.servicos.NavioService;

@Stateless
public class GerenciarNaviosServiceImpl implements GerenciarNaviosService {

	private static final long serialVersionUID = -4378211944005524091L;

	@EJB
	NavioService navioService;

	public NavioService getNavioService() {
		return navioService;
	}

	public void setNavioService(NavioService navioService) {
		this.navioService = navioService;
	}

	@Override
	public List<Navio> listaNaviosComCarga(AgenteCarga agente) {
		List<Navio> resultList = new ArrayList<Navio>();

		try {

			CriteriaBuilder builder = navioService.getCriteriaBuilder();
			CriteriaQuery<Navio> criteria = builder.createQuery(Navio.class);
			Root<Navio> navioRoot = criteria.from(Navio.class);
			criteria.select(navioRoot);
			resultList = navioService.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
	
	@Override
	public Navio obterPorId(Integer idNavio)
	{
		if(idNavio!=null && idNavio > 0) return this.navioService.obterPorId(idNavio);
		else return null;
	}
	
	
	
	
}
