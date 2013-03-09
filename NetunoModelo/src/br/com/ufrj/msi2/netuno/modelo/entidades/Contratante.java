package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * � a pessoa f�sica ou jur�dica que contrata os servi�os da empresa.
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="contratante")
@PrimaryKeyJoinColumn(name="id")
public class Contratante extends Usuario {
	private static final long serialVersionUID = -837838116953606430L;
	
	@OneToMany
	@JoinColumn(name="contratante_id")
	private List<Contrato> contratos;

	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	public String getTipo() {
		return "contratante";
	}

}
