package br.com.ufrj.msi2.netuno.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;

@ManagedBean(name="empTransModel")
@ViewScoped
public class EmpTransModelBean {

	private EmpresaTransporte empTrans;

	public EmpresaTransporte getEmpTrans() {
		if(empTrans == null){
			empTrans = new EmpresaTransporte();
		}
		return empTrans;
	}

	public void setEmpTrans(EmpresaTransporte empTrans) {
		this.empTrans = empTrans;
	}
}