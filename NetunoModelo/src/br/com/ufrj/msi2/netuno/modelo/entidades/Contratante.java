package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="contratante")
public class Contratante extends Usuario {
	private static final long serialVersionUID = -837838116953606430L;
	
	@OneToMany
	@JoinColumn(name="contratante_id")
	private List<Contrato> contrato;

	public List<Contrato> getContrato() {
		return contrato;
	}

	public void setContrato(List<Contrato> contrato) {
		this.contrato = contrato;
	}
	
	public String getTipo() {
		return "contratante";
	}

}
