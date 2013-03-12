package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;

@ManagedBean(name="atendenteModel")
@ViewScoped
public class AtendenteModelBean extends MBean {
	
	private CPF cpf;

	@PostConstruct
	public void construct() {
		
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

}
