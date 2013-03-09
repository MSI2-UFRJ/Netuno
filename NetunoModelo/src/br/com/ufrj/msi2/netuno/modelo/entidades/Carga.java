package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Objeto que o Contratante quer enviar.
 * Uma Carga pode ser dividida em partes, caso um conteiner não tenha espaço ou peso restante suficiente para comportá-la. 
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="carga")
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy=InheritanceType.JOINED)
public class Carga extends CargaComponente {
	private static final long serialVersionUID = 3412494703857073751L;
	
	@OneToMany(mappedBy="carga")
    private List<ParteCarga> partes;
	
	@ManyToOne
	@JoinColumn(name="contrato_id")
	private Contrato contrato;

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<ParteCarga> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteCarga> partes) {
		this.partes = partes;
	}

}
