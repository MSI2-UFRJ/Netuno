package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries(
		{
			@NamedQuery(name="Navio.recuperarTodos", query="select n from Navio n")
		}
)

@Entity
@Table(name="navio")
public class Navio implements Serializable {
	private static final long serialVersionUID = 5809941224721488777L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@OneToMany(mappedBy="navio")
	private List<Atraque> atraque;

	public List<Atraque> getAtraque() {
		return atraque;
	}

	public void setAtraque(List<Atraque> atraque) {
		this.atraque = atraque;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
