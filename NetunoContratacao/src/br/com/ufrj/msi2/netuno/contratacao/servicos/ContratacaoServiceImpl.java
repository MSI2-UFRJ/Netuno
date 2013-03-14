package br.com.ufrj.msi2.netuno.contratacao.servicos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaLogService;
import br.com.ufrj.msi2.netuno.modelo.servicos.CargaService;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratanteService;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoService;

@Stateless
public class ContratacaoServiceImpl implements ContratacaoService {
	private static final long serialVersionUID = -7087952795393582189L;
	
	@EJB
	CargaService cargaService;
	
	@EJB
	CargaLogService cargaLogService;
	
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
		
		if(contrato.getEnderecoColeta() == null) {
			contratoService.salvarContrato(contrato);
		} else {
			contratoService.salvarContratoAguardandoColeta(contrato);
		}

		for(Carga c : contrato.getCargas()) {
			cargaService.salvar(c);
			
			CargaLog cargaLog = cargaLogService.criarCargaLog();
			cargaLog.setCarga(c);
			cargaLog.setData(new Date());
			
			
			if(contrato.getEnderecoColeta() == null) {
				cargaLog.setDescricao("Aguardando carga ser entregue ao porto origem");
			} else {
				cargaLog.setDescricao("Aguardando coleta da carga");
			}
			
			cargaLogService.salvarCargaLog(cargaLog);
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
	
	public void calcularPreco(Contrato contrato, boolean temColeta, boolean temEntrega) {
		BigDecimal preco = new BigDecimal(0.0);
		
		for(Carga c : contrato.getCargas()) {
			if(c.isCargaPerecivel()) {
				preco = preco.add(new BigDecimal(100.0));
			}
			
			if(temColeta) {
				preco = preco.add(new BigDecimal(50.0));
			}
			
			if(temEntrega) {
				preco = preco.add(new BigDecimal(50.0));
			}
			
			if(c.getPeso() <= 10.0) {
				preco = preco.add(new BigDecimal(50.0));
			} else if(c.getPeso() <= 100.0) {
				preco = preco.add(new BigDecimal(500.0));
			} else if(c.getPeso() <= 500.0) {
				preco = preco.add(new BigDecimal(2500.0));
			} else if(c.getPeso() <= 1000.0) {
				preco = preco.add(new BigDecimal(5000.0));
			} else if(c.getPeso() <= 2000.0) {
				preco = preco.add(new BigDecimal(10000.0));
			} else {
				preco = preco.add(new BigDecimal(c.getPeso()));
			} 
		}
		
		contrato.setPreco(preco);
	}
	
	public Contratante recuperaContratantePorCPF(CPF cpf) throws ResultadoNaoEncontradoException {
		return this.contratanteService.recuperaPorCPF(cpf);
	}
	
	public Contrato recuperaContratoComCargas(Contrato contrato) {
		return this.contratoService.recuperaContratoComCargas(contrato);
	}
	
	public CargaLog recuperaUltimoCargaLogDeCarga(Carga carga) {
		return this.cargaLogService.recuperaUltimoCargaLogDeCarga(carga);
	}
	
	public Contratante criarContratante() {
		return this.contratanteService.criarContratante();
	}
	
	public void salvarContratante(Contratante contratante) {
		this.contratanteService.salvarContratante(contratante);
	}

	public CargaService getCargaService() {
		return cargaService;
	}

	public CargaLogService getCargaLogService() {
		return cargaLogService;
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

	public void setCargaLogService(CargaLogService cargaLogService) {
		this.cargaLogService = cargaLogService;
	}

	public void setContratanteService(ContratanteService contratanteService) {
		this.contratanteService = contratanteService;
	}

	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}

}
