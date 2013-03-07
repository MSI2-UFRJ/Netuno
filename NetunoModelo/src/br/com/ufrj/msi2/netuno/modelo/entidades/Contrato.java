package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries(
	{
		@NamedQuery(name="Contrato.recuperaPorContratante",
					query="select contrato from Contratante contratante inner join contratante.contratos contrato where contratante = :contratante"
		)
	}
)
@Entity
@Table(name="contrato")
public class Contrato implements Serializable {
	private static final long serialVersionUID = -5051560792148827601L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany
	@JoinColumn(name="contrato_id")  
	private List<Carga> cargas;
	
	@ManyToOne
    private Porto portoOrigem;
	
	@ManyToOne
    private Porto portoDestino;
	
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@Column(name="finalizado")
	private boolean finalizado;

	public Integer getId() {
		return id;
	}

	public List<Carga> getCargas() {
		return cargas;
	}

	public Porto getPortoOrigem() {
		return portoOrigem;
	}

	public Porto getPortoDestino() {
		return portoDestino;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCargas(List<Carga> cargas) {
		this.cargas = cargas;
	}

	public void setPortoOrigem(Porto portoOrigem) {
		this.portoOrigem = portoOrigem;
	}

	public void setPortoDestino(Porto portoDestino) {
		this.portoDestino = portoDestino;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

}
