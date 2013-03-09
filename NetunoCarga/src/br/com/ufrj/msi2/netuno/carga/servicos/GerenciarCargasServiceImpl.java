package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaService;

@Stateless
public class GerenciarCargasServiceImpl implements GerenciarCargasService {
	private static final long serialVersionUID = -7877957927931528709L;
	
	@EJB
	CargaService cargaService;

	public CargaService getCargaService() {
		return cargaService;
	}

	public void setCargaService(CargaService cargaService) {
		this.cargaService = cargaService;
	}

	@Override
	public List<Carga> listaCargasAgente(AgenteCarga agente) {
		List<Carga> resultList = new ArrayList<Carga>();

		try {

			CriteriaBuilder builder = cargaService.getCriteriaBuilder();
			CriteriaQuery<Carga> criteria = builder.createQuery(Carga.class);
			Root<Carga> cargaRoot = criteria.from(Carga.class);
			Root<AgenteCarga> agenteEmbRoot = criteria.from(AgenteCarga.class);
			criteria.where(builder.equal(agenteEmbRoot, cargaRoot.get("agenteEmbarque")));
			
			resultList = cargaService.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
}
