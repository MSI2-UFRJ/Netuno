package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.ufrj.msi2.netuno.modelo.enums.SituacaoContratoEnum;

/**
 * É feito por um Contratante que quer enviar cargas de um porto origem para um porto destino.
 * @author Thiago, Paula
 *
 */
@NamedQueries(
	{
		@NamedQuery(name="Contrato.recuperaPorContratante",
					query="select contrato from Contratante contratante inner join contratante.contratos contrato where (contratante = :contratante and contrato.situacao = :situacao)"
		),
		@NamedQuery(name="Contrato.recuperaPorId",
					query="select contrato from Contrato contrato where contrato.id = :id"
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
	
	@ManyToOne(targetEntity = Porto.class)
    private Porto portoDestino;
	
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@Column(name="data_estimada")
	private Date dataEstimada;
	
	@Column(name="situacao")
	@Enumerated(EnumType.STRING)  
	private SituacaoContratoEnum situacao;
	
	@Column(name="endereco_coleta")
	private String enderecoColeta;
	
	@Column(name="endereco_entrega")
	private String enderecoEntrega;

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

	public Date getDataEstimada() {
		return dataEstimada;
	}

	public SituacaoContratoEnum getSituacao() {
		return situacao;
	}

	public String getEnderecoColeta() {
		return enderecoColeta;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
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

	public void setDataEstimada(Date dataEstimada) {
		this.dataEstimada = dataEstimada;
	}

	public void setSituacao(SituacaoContratoEnum situacao) {
		this.situacao = situacao;
	}

	public void setEnderecoColeta(String enderecoColeta) {
		this.enderecoColeta = enderecoColeta;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

}
