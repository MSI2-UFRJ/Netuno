package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * A unidade mínima para o transporte de cargas na empresa. Todas as cargas
 * devem ser transportadas em conteiners. Conteiners podem ser embarcados ou
 * desembarcados de navios.
 * 
 * @author Thiago, Paula
 * 
 */
@NamedQueries({
		@NamedQuery(name = "Conteiner.recuperarTodos", query = "select c from Conteiner c"),
		@NamedQuery(name = "Conteiner.recuperarPorNavio", query = "select c from Conteiner c where c.navio = :navio"),
		
		//TODO and a.data_chegada <= NOW and ( a.data_saida > NOW or a.data_saida = NULL)
		@NamedQuery(name = "Conteiner.recuperarPorNavioPorAgenteParaDesembarque", query = "select c from Conteiner c, Atraque a, CargaComponente cc	where a.navio = :navio and c.navio = :navio	and a.porto = :porto and cc.conteiner = c and cc.agenteDesembarque = :agente")
		
})
@Entity
@Table(name = "conteiner")
public class Conteiner implements Serializable {
	private static final long serialVersionUID = 1908602122767901644L;

	private static final int pesoMaximo = 20000;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "pesoDisponivel")
	private Double pesoDisponivel;

	@OneToMany
	@JoinColumn(name = "conteiner_id")
	private List<CargaComponente> cargasComponente;

	@ManyToOne(targetEntity = Porto.class)
	private Porto porto;

	@ManyToOne(targetEntity = Navio.class)
	@JoinColumn(name = "navio_id")
	private Navio navio;

	public Navio getNavio() {
		return navio;
	}

	public void setNavio(Navio navio) {
		this.navio = navio;
	}

	public Integer getId() {
		return id;
	}

	public List<CargaComponente> getCargasComponente() {
		return cargasComponente;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCargasComponente(List<CargaComponente> cargasComponente) {
		this.cargasComponente = cargasComponente;
	}

	public static int getPesomaximo() {
		return pesoMaximo;
	}

	public Double getPesoDisponivel() {
		return pesoDisponivel;
	}

	public void setPesoDisponivel(Double pesoDisponivel) {
		this.pesoDisponivel = pesoDisponivel;
	}

	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}
}
