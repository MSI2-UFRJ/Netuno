package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ValidacaoException;

@Local
public interface ContratanteService extends Serializable {
	
	public Contratante criarContratante();

	public void salvarContratante(Contratante contratante) throws ValidacaoException;

	public Contratante recuperaPorIdComContratos(Integer id);

	public Contratante recuperaPorCPF(CPF cpf) throws ResultadoNaoEncontradoException;

}
