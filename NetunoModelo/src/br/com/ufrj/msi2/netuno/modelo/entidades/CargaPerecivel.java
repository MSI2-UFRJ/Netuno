package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Carga que tem data de validade.
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="carga_perecivel")
public class CargaPerecivel extends Carga {
	private static final long serialVersionUID = 3046937145061148267L;

	@Column(name="data_validade")
	private Date dataValidade;

	public Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

}
