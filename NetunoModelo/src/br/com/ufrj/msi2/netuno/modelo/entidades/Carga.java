package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Objeto que o Contratante quer enviar.
 * Uma Carga pode ser dividida em partes, caso um conteiner não tenha espaço ou peso restante suficiente para comportá-la. 
 * @author Thiago, Paula
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="carga")
public class Carga extends CargaComponente {
	private static final long serialVersionUID = 3412494703857073751L;
	
	@OneToMany(mappedBy="carga")
    private List<ParteCarga> partes;

	public List<ParteCarga> getPartes() {
		return partes;
	}

	public void setPartes(List<ParteCarga> partes) {
		this.partes = partes;
	}

}
