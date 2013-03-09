package br.com.ufrj.msi2.netuno.contratacao.servicos;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoService;

@Stateless
public class ContratacaoServiceImpl implements ContratacaoService {
	private static final long serialVersionUID = -7087952795393582189L;

	@EJB
	ContratoService contratoService;
	
	public Contrato criarContrato() {
		return this.contratoService.criarContrato();
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

	public ContratoService getContratoService() {
		return contratoService;
	}

	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}

}
