package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Partes de uma Carga de um contrato, alocadas em conteiners diferentes.
 *
 */
@NamedQueries(
		{
			@NamedQuery(
					name="ParteCarga.listaParteCargasParaDesembarque",
					query="select parteCarga from ParteCarga parteCarga where parteCarga.agenteDesembarque.id = :agente and parteCarga.patio.porto.id = :porto"
			)
		}
)
@Entity
@Table(name="parte_carga")
@PrimaryKeyJoinColumn(name="id")
public class ParteCarga extends CargaComponente {
	private static final long serialVersionUID = 8237313467233955825L;
	
	@ManyToOne
	@JoinColumn(name="carga_id")
    private Carga carga;
		
	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

}
