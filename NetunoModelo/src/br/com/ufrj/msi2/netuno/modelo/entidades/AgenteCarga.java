package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Funcionário do porto responsável pela alocação e retirada de cargas de conteiners, além do embarque e desembarque de conteiners de navios.
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="agenteCarga")
@PrimaryKeyJoinColumn(name="id")
public class AgenteCarga extends Usuario {
	private static final long serialVersionUID = -4587241605092091433L;
	
	@OneToMany
	@JoinColumn(name="agenteEmbarque_id")
	private List<CargaComponente> cargasComponenteAEmbarcar;
	
	@OneToMany
	@JoinColumn(name="agenteDesembarque_id")
	private List<CargaComponente> cargasComponenteADesembarcar;

	public List<CargaComponente> getCargasComponenteAEmbarcar() {
		return cargasComponenteAEmbarcar;
	}

	public List<CargaComponente> getCargasComponenteADesembarcar() {
		return cargasComponenteADesembarcar;
	}

	public void setCargasComponenteAEmbarcar(
			List<CargaComponente> cargasComponenteAEmbarcar) {
		this.cargasComponenteAEmbarcar = cargasComponenteAEmbarcar;
	}

	public void setCargasComponenteADesembarcar(
			List<CargaComponente> cargasComponenteADesembarcar) {
		this.cargasComponenteADesembarcar = cargasComponenteADesembarcar;
	}
	
	public String getTipo() {
		return "agenteCarga";
	}

}
