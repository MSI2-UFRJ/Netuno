package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NamedQueries(
		{
			@NamedQuery(name="Porto.recuperaPorId",
				query="select porto from Porto porto where porto.id = :id")
		}
)

@Entity
@Table(name = "porto", uniqueConstraints = @UniqueConstraint(columnNames = {
		"localizacao", "nome" }))
public class Porto implements Serializable {
	private static final long serialVersionUID = -4513783540480470840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToMany(mappedBy = "pertence")
	private List<AgentePorto> agentes;

	@OneToMany(mappedBy = "porto", cascade=CascadeType.REMOVE)
	private List<Atraque> atraques;

	@OneToMany(mappedBy = "porto", cascade=CascadeType.REMOVE)
	private List<Patio> patios;

	@OneToMany(mappedBy = "porto", cascade=CascadeType.REMOVE)
	private List<Slot> slots;

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public List<Atraque> getAtraques() {
		return atraques;
	}

	public void setAtraques(List<Atraque> atraques) {
		this.atraques = atraques;
	}

	public List<Patio> getPatios() {
		return patios;
	}

	public void setPatios(List<Patio> patios) {
		this.patios = patios;
	}

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "localizacao", nullable = false)
	private String localizacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<AgentePorto> getAgentes() {
		return agentes;
	}

	public void setAgentes(List<AgentePorto> agentes) {
		this.agentes = agentes;
	}

	@Override
	public boolean equals(Object arg0) {
		Porto porto = (Porto) arg0;

		if (porto == null) {
			return false;
		}

		return this.id == porto.getId();
	}
	
	public String getNomeLocalizacao(){
		return this.nome+" - "+this.localizacao;
	}

}
