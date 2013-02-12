package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
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
