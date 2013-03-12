package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;

@Local
public interface ContratanteService extends Serializable {
	
	public void salvarContratante(Contratante contratante);
	public Contratante recuperaPorId(Integer id);
	public Contratante recuperaPorCPF(CPF cpf) throws ResultadoNaoEncontradoException;

}
