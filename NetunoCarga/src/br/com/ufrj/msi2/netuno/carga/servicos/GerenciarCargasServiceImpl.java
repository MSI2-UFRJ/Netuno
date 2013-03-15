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
			
			ArrayList<Predicate> predicados = new ArrayList<Predicate>();
			
			Expression<String> agenteEmbarque = cargaRoot.get("agenteEmbarque");
			predicados.add(builder.equal(builder.lower(agenteEmbarque),agente.getId()));

			Expression<String> alocacao = cargaRoot.get("alocacaoCompleta");
			predicados.add(builder.equal((alocacao),false));

			Expression<String> conteiner = cargaRoot.get("conteiner");
			predicados.add(builder.isNull(conteiner));
			
			Expression<String> desembarcada = cargaRoot.get("desembarcada");
			predicados.add(builder.equal((desembarcada),false));
			
			criteria.select(cargaRoot).where(predicados.toArray(new Predicate[]{}));
			
			resultList = cargaService.filtrar(criteria);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return resultList;
	}
	
	@Override
	public List<Carga> listaCargasParaDesembarque(AgenteCarga agente) {	
		List<ParteCarga> partes = this.parteCargaService.listaParteCargasDisponiveis(agente);

		List<Carga> listCarga = new ArrayList<Carga>();
		
		
		for (ParteCarga parteCarga : partes) {
			boolean found = false;
			for(Carga carga : listCarga)
			{
				if(carga.getId() == parteCarga.getCarga().getId())
				{
					found = true;
					break;
				}
			}
			if(!found)
			{
				listCarga.add(parteCarga.getCarga());
			}
		}
		
		return listCarga;
	}
	
	@Override
	public List<ParteCarga> listaParteCargasComConteiner(Carga carga) {
		List<ParteCarga> resultList = new ArrayList<ParteCarga>();

		try {

			CriteriaBuilder builder = cargaService.getCriteriaBuilder();
			CriteriaQuery<ParteCarga> criteria = builder.createQuery(ParteCarga.class);
			Root<ParteCarga> root = criteria.from(ParteCarga.class);
			
			ArrayList<Predicate> predicados = new ArrayList<Predicate>();
			
			Expression<String> exp = root.get("carga");
			predicados.add(builder.equal(builder.lower(exp),carga.getId()));
			
			criteria.select(root).where(predicados.toArray(new Predicate[]{}));
			
			resultList = parteCargaService.filtrar(criteria);
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
		
		carga = this.obterPorId(carga.getId());
		
		carga.setPartes(this.listaParteCargasComConteiner(carga));
		
		//peso ainda a ser alocado
		double peso = carga.getPeso();
		if(peso > 0)
		{
				
			//se for null, a carga nunca foi alocada
			if(carga.getPartes() != null)
			{
				for (ParteCarga parte : carga.getPartes()) {
					peso -= parte.getPeso();
				}
			}
			
			ParteCarga parteCarga = new ParteCarga();
			parteCarga.setCarga(carga);
			parteCarga.setConteiner(conteiner);
			
			double pesoParte = (peso > conteiner.getPesoDisponivel()) ? conteiner.getPesoDisponivel() : peso;
			parteCarga.setPeso(pesoParte);
			if(carga.getAgenteEmbarque() != null) { parteCarga.setAgenteEmbarque(carga.getAgenteEmbarque()); }
			if(carga.getAgenteDesembarque() != null) { parteCarga.setAgenteDesembarque(carga.getAgenteDesembarque()); }
			
			parteCargaService.salvar(parteCarga);
			
			if(peso - pesoParte <= 0)
			{
				carga.setAlocacaoCompleta(true);
				this.cargaService.salvar(carga);
			}
			
			gConteinerService.AtualizaPeso(conteiner, conteiner.getPesoDisponivel() - parteCarga.getPeso());
		}
	}

	@Override
	public void desalocarParteCarga(int parteId) {
		ParteCarga parte = parteCargaService.obterPorId(parteId);
		parte.setDesembarcada(true);
		parte.setConteiner(null);
		parteCargaService.salvar(parte);
		
		this.desalocarCarga(parte.getCarga());
	}
	
	private void desalocarCarga(Carga carga) {
		carga = cargaService.obterPorId(carga.getId());
		boolean todasOk = true;
		for(ParteCarga parte : carga.getPartes())
		{
			if(!parte.isDesembarcada())
			{
				todasOk = false;
				break;
			}
		}
		
		if(todasOk)
		{
			carga.setDesembarcada(true);
			cargaService.salvar(carga);
		}
	}

	@Override
	public void desalocarTodasPartes(int cargaId) {
		Carga carga = cargaService.obterPorId(cargaId);
		for(ParteCarga parte : carga.getPartes())
		{
			parte.setDesembarcada(true);
			parte.setConteiner(null);
			parteCargaService.salvar(parte);
			
			this.desalocarCarga(parte.getCarga());
		}
		
		carga.setDesembarcada(true);
		cargaService.salvar(carga);
	}
}
