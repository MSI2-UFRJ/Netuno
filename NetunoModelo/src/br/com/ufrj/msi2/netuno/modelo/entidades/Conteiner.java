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

/**
 * A unidade mínima para o transporte de cargas na empresa.
 * Todas as cargas devem ser transportadas em conteiners.
 * Conteiners podem ser embarcados ou desembarcados de navios.
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="conteiner")
public class Conteiner implements Serializable {
	private static final long serialVersionUID = 1908602122767901644L;

	private static final int pesoMaximo = 20000;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "pesoDisponivel")
	private Double pesoDisponivel;

	@OneToMany
	@JoinColumn(name="conteiner_id")  
	private List<CargaComponente> cargasComponente;
	
	@ManyToOne(targetEntity = Porto.class)
    private Porto portoOrigem;

	@ManyToOne(targetEntity = Porto.class)
    private Porto portoDestino;

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
	

	public Porto getPortoOrigem() {
		return portoOrigem;
	}

	public void setPortoOrigem(Porto portoOrigem) {
		this.portoOrigem = portoOrigem;
	}

	public Porto getPortoDestino() {
		return portoDestino;
	}

	public void setPortoDestino(Porto portoDestino) {
		this.portoDestino = portoDestino;
	}

}
