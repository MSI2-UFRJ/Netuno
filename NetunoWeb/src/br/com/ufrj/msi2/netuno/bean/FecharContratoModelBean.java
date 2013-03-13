package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.enums.FormaPagamentoEnum;

@ManagedBean(name="fecharContratoModel")
@ViewScoped
public class FecharContratoModelBean extends MBean {
	
	private Contratante contratante;
	
	private Contrato contrato;
	
	private FormaPagamentoEnum[] formasPagamento;

	@PostConstruct
	public void construct() {
		
	}

	public Contratante getContratante() {
		return contratante;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public FormaPagamentoEnum[] getFormasPagamento() {
		return formasPagamento;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public void setFormasPagamento(FormaPagamentoEnum[] formasPagamento) {
		this.formasPagamento = formasPagamento;
	}

}
