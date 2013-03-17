package br.com.ufrj.msi2.netuno.contratacao.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ValidacaoException;

@Local
public interface ContratacaoService extends Serializable {

	public Contrato criarContrato();

	public void salvarContrato(Contratante contratante, Contrato contrato) throws ValidacaoException;

	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante);

	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante);

	public void estimarDataEntrega(Contrato contrato, boolean temColeta, boolean temEntrega);

	public void calcularPreco(Contrato contrato, boolean temColeta, boolean temEntrega);

	public Contrato recuperaContratoComCargas(Contrato contrato);
	
	public CargaLog recuperaUltimoCargaLogDeCarga(Carga carga);
	
	public Contratante recuperaContratantePorCPF(CPF cpf) throws ResultadoNaoEncontradoException;
	
	public Contratante criarContratante();
	
	public void salvarContratante(Contratante contratante) throws ValidacaoException;
	
	public boolean existeUsuarioComCPF(CPF cpf);
	
	public boolean existeUsuarioComLogin(String login);

}
