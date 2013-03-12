package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Embeddable;

@Embeddable
public class CPF {
	
	private String cpf;
	
	public CPF() {
		
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
