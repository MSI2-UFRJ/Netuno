package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cargalog")
public class CargaLog implements Serializable {
	private static final long serialVersionUID = 5311520249046025239L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
    private Carga carga;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "descricao")
	private String descricao;

	public Integer getId() {
		return id;
	}

	public Carga getCarga() {
		return carga;
	}

	public Date getData() {
		return data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
