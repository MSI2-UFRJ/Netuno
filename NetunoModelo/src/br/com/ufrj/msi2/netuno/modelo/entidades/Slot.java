package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="slot")
public class Slot implements Serializable{

	private static final long serialVersionUID = 8800603294168019536L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="slot")
	private List<Atraque> atraques;
	
	@ManyToOne
	private Porto porto;
	
	public Porto getPorto() {
		return porto;
	}

	public void setPorto(Porto porto) {
		this.porto = porto;
	}

	public List<Atraque> getAtraques() {
		return atraques;
	}

	public void setAtraques(List<Atraque> atraques) {
		this.atraques = atraques;
	}
}
