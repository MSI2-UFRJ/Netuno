package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;

@ManagedBean(name="contratanteModel")
@ViewScoped
public class ContratanteModelBean extends MBean {

	private Contratante contratante;

	private String confirmacaoSenha;

	@PostConstruct
	public void construct() {
		
	}

	public Contratante getContratante() {
		return contratante;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

}
