package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NamedQueries(
		{
			@NamedQuery(name="Pregao.recuperaPregoesAtivos",
						query="from Pregao"
			)
		}
)

@Entity
@Table(name="pregao")
public class Pregao implements Serializable{

	private static final long serialVersionUID = -8454333668773556668L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	private Carga anuncia;

	@ManyToOne
	private AgenteLogistica abertoPor;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Carga getAnuncia() {
		return anuncia;
	}

	public void setAnuncia(Carga anuncia) {
		this.anuncia = anuncia;
	}

	public AgenteLogistica getAbertoPor() {
		return abertoPor;
	}

	public void setAbertoPor(AgenteLogistica abertoPor) {
		this.abertoPor = abertoPor;
	}
}
