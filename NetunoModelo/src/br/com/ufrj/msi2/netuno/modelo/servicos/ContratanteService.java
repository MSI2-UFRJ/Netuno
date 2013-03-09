package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;

@Local
public interface ContratanteService extends Serializable {
	
	public void salvarContratante(Contratante contratante);
	public Contratante recuperaPorId(Integer id);

}
