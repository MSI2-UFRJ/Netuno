package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaService;
import br.com.ufrj.msi2.netuno.modelo.servicos.ParteCargaService;

@Stateless
public class GerenciarCargasServiceImpl implements GerenciarCargasService {
	private static final long serialVersionUID = -7877957927931528709L;
	
	@EJB
	CargaService cargaService;
	@EJB
	ParteCargaService parteCargaService;
	@EJB
	GerenciarConteinersService gConteinerService;
	
	public CargaService getCargaService() {
		return cargaService;
	}
	
	public void setCargaService(CargaService cargaService) {
		this.cargaService = cargaService;
	}

	@Override
	public List<Carga> listaCargasParaEmbarque(AgenteCarga agente) {
		List<Carga> resultList = new ArrayList<Carga>();

		try {

			CriteriaBuilder builder = cargaService.getCriteriaBuilder();
			CriteriaQuery<Carga> criteria = builder.createQuery(Carga.class);
			Root<Carga> cargaRoot = criteria.from(Carga.class);
			
			Expression<String> agenteEmbarque = cargaRoot.get("agenteEmbarque");
			criteria.select(cargaRoot).where(builder.equal(builder.lower(agenteEmbarque),agente.getId()));
			
			resultList = cargaService.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
	
	@Override
	public Carga obterPorId(Integer idCarga)
	{
		if(idCarga!=null && idCarga > 0) return this.cargaService.obterPorId(idCarga);
		else return null;
	}

	@Override
	public void alocarCarga(Carga carga, Conteiner conteiner) {
		//peso ainda a ser alocado
		double peso = carga.getPeso();
		//se for null, a carga nunca foi alocada
		if(carga.getPartes() == null)
		{
			for (ParteCarga parte : carga.getPartes()) {
				peso -= parte.getPeso();
			}
		}
		
		ParteCarga parteCarga = new ParteCarga();
		parteCarga.setCarga(carga);
		parteCarga.setConteiner(conteiner);
		parteCarga.setPeso((peso > conteiner.getPesoDisponivel()) ? conteiner.getPesoDisponivel() : peso);
		if(carga.getAgenteEmbarque() != null) { parteCarga.setAgenteEmbarque(carga.getAgenteEmbarque()); }
		if(carga.getAgenteDesembarque() != null) { parteCarga.setAgenteDesembarque(carga.getAgenteDesembarque()); }
		parteCargaService.salvar(parteCarga);
		
		gConteinerService.AtualizaPeso(conteiner, conteiner.getPesoDisponivel() - parteCarga.getPeso());
	}
}
