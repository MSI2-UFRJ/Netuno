package br.com.ufrj.msi2.netuno.logistica.servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;
import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;
import br.com.ufrj.msi2.netuno.modelo.servicos.PregaoService;
import br.com.ufrj.msi2.netuno.modelo.servicos.EmpTransService;
import br.com.ufrj.msi2.netuno.modelo.servicos.RepresEmpTransService;

@Stateless
public class LogisticaServiceImpl implements LogisticaService{

	private static final long serialVersionUID = 4487980598535994706L;

	@EJB
	PregaoService pregaoService;
	
	@EJB
	EmpTransService empTransService;
	
	@EJB
	RepresEmpTransService represEmpTransService;
	
	public PregaoService getPregaoService() {
		return pregaoService;
	}

	public void setPregaoService(PregaoService pregaoService) {
		this.pregaoService = pregaoService;
	}

	public EmpTransService getEmpTransService() {
		return empTransService;
	}

	public void setEmpTransService(EmpTransService empTransService) {
		this.empTransService = empTransService;
	}

	public List<Pregao> recuperaPregoesAbertos() {
		return pregaoService.recuperaPregoesAbertos();
	}
	
	public void salvarEmpTrans(EmpresaTransporte empTrans) {
		empTransService.salvar(empTrans);
	}

	public void salvarRepresEmpTrans(RepresEmpTrans repEmpTrans) {
		represEmpTransService.salvar(repEmpTrans);
	}

}
