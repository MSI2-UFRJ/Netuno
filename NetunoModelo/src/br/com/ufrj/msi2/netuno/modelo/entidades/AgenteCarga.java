package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="agenteCarga")
@PrimaryKeyJoinColumn(name="id")
public class AgenteCarga extends Usuario implements Serializable {
	private static final long serialVersionUID = -4587241605092091433L;

	private Integer id;
	
	@OneToMany
	@JoinColumn(name="agenteEmbarque_id")
	private List<CargaComponente> cargasComponenteAEmbarcar;
	
	@OneToMany
	@JoinColumn(name="agenteDesembarque_id")
	private List<CargaComponente> cargasComponenteADesembarcar;

	public Integer getId() {
		return id;
	}

	public List<CargaComponente> getCargasComponenteAEmbarcar() {
		return cargasComponenteAEmbarcar;
	}

	public List<CargaComponente> getCargasComponenteADesembarcar() {
		return cargasComponenteADesembarcar;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCargasComponenteAEmbarcar(
			List<CargaComponente> cargasComponenteAEmbarcar) {
		this.cargasComponenteAEmbarcar = cargasComponenteAEmbarcar;
	}

	public void setCargasComponenteADesembarcar(
			List<CargaComponente> cargasComponenteADesembarcar) {
		this.cargasComponenteADesembarcar = cargasComponenteADesembarcar;
	}

}
