package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Objeto que o Contratante quer enviar.
 * Uma Carga pode ser dividida em partes, caso um conteiner não tenha espaço ou peso restante suficiente para comportá-la. 
 * @author Thiago, Paula
 *
 */

@NamedQueries(
		{
			@NamedQuery(name="Carga.recuperaCargasSemPregao",
						query="from Carga as carga where carga not in (select carga from Pregao as pregao inner join pregao.anuncia as carga)"
			)
		}
)

@Entity
@Table(name="carga")
@PrimaryKeyJoinColumn(name="id")
@Inheritance(strategy=InheritanceType.JOINED)
public class Carga extends CargaComponente {
	private static final long serialVersionUID = 3412494703857073751L;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToMany(mappedBy="carga")
    private List<ParteCarga> partes;
	
	@ManyToOne
	@JoinColumn(name="contrato_id")
	private Contrato contrato;
	
	public boolean isCargaPerecivel() {
		return false;
	}
	
	public Date getDataValidade() {
		return null;
	}

	public void setDataValidade(Date dataValidade) {
		
	}

	public String getDescricao() {
		return descricao;
	}

	public List<ParteCarga> getPartes() {
		return partes;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPartes(List<ParteCarga> partes) {
		this.partes = partes;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

}
