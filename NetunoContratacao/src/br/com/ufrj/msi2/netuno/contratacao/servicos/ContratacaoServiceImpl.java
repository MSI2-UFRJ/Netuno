package br.com.ufrj.msi2.netuno.contratacao.servicos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaService;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratanteService;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoService;

@Stateless
public class ContratacaoServiceImpl implements ContratacaoService {
	private static final long serialVersionUID = -7087952795393582189L;
	
	@EJB
	CargaService cargaService;
	
	@EJB
	ContratanteService contratanteService;

	@EJB
	ContratoService contratoService;
	
	public Contrato criarContrato() {
		return this.contratoService.criarContrato();
	}
	
	public void salvarContrato(Contratante contratante, Contrato contrato) {
		Contratante contratanteComContratosCarregados = contratanteService.recuperaPorId(contratante.getId());
		contratanteComContratosCarregados.getContratos().add(contrato);

		contrato.setDataCriacao(new Date());
		
		contratanteService.salvarContratante(contratanteComContratosCarregados);
		
		contratoService.salvarContrato(contrato);

		for(Carga c : contrato.getCargas()) {
			cargaService.salvar(c);
		}
	}

	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante) {
		return contratoService.recuperaContratosAbertosPorContratante(contratante);
	}
	
	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante) {
		return contratoService.recuperaContratosFechadosPorContratante(contratante);
	}
	
	public void estimarDataEntrega(Contrato contrato, boolean temColeta, boolean temEntrega) {
		if(contrato.getPortoOrigem() == null || contrato.getPortoDestino() == null) {
			contrato.setDataEstimada(null);
		} else {
			Date date = new Date();

			Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.set(Calendar.HOUR, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.SECOND, 0);
	        
	        if(temColeta) {
	        	cal.add(Calendar.DATE, 1);
	        }
	        
	        if(temEntrega) {
	        	cal.add(Calendar.DATE, 1);
	        }
	        
	        cal.add(Calendar.DATE, 6);

	        contrato.setDataEstimada(cal.getTime());
		}
	}
	
	public Contrato recuperaContratoComCargas(Contrato contrato) {
		return this.contratoService.recuperaContratoComCargas(contrato);
	}

	public CargaService getCargaService() {
		return cargaService;
	}

	public ContratanteService getContratanteService() {
		return contratanteService;
	}

	public ContratoService getContratoService() {
		return contratoService;
	}

	public void setCargaService(CargaService cargaService) {
		this.cargaService = cargaService;
	}

	public void setContratanteService(ContratanteService contratanteService) {
		this.contratanteService = contratanteService;
	}

	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}

}
