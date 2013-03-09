package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="represemptrans")
@PrimaryKeyJoinColumn(name="id")
public class RepresEmpTrans extends Usuario {

	private static final long serialVersionUID = -244112269232208899L;

	@Override
	public String getTipo() {
		return "represemptrans";
	}
	
	@ManyToOne
	private EmpresaTransporte representa;

	public EmpresaTransporte getRepresenta() {
		return representa;
	}

	public void setRepresenta(EmpresaTransporte representa) {
		this.representa = representa;
	}

}
