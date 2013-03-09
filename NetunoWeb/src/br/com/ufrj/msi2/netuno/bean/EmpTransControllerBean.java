package br.com.ufrj.msi2.netuno.bean;

import java.util.List;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.logistica.servicos.LogisticaService;
import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;
import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;

@ManagedBean(name="empTransController")
@ViewScoped
public class EmpTransControllerBean {

	@EJB
	LogisticaService logisticaService;
	
	@ManagedProperty(value="#{empTransModel}")
	private EmpTransModelBean empTransModelBean;
	
	@PostConstruct
	public void construct(){
		listRepToInsert = new ArrayList<RepresEmpTrans>();
	}
	
	public EmpTransModelBean getEmpTransModelBean() {
		return empTransModelBean;
	}

	public void setEmpTransModelBean(EmpTransModelBean empTransModelBean) {
		this.empTransModelBean = empTransModelBean;
	}

	private List<RepresEmpTrans> listRepToInsert;
	
	public LogisticaService getLogisticaService() {
		return logisticaService;
	}

	public void setLogisticaService(LogisticaService logisticaService) {
		this.logisticaService = logisticaService;
	}

	public List<RepresEmpTrans> getListRepToInsert() {
		return listRepToInsert;
	}

	public void setListRepToInsert(List<RepresEmpTrans> listRepToInsert) {
		this.listRepToInsert = listRepToInsert;
	}
	
	public String salvar(){
		EmpresaTransporte emp = empTransModelBean.getEmpTrans();
		emp.setRepresentantes(listRepToInsert);
		logisticaService.salvarEmpTrans(emp);
		for(RepresEmpTrans r : listRepToInsert)
			logisticaService.salvarRepresEmpTrans(r);
		return "voltarLogistica";
	}
	
	public void preparaInserirRep(){
		RepresEmpTrans rep = new RepresEmpTrans();
		rep.setRepresenta(empTransModelBean.getEmpTrans());
		listRepToInsert.add(rep);
	}
}
