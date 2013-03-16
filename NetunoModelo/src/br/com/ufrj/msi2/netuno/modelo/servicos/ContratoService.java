package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@Local
public interface ContratoService extends Serializable {
	
	public Contrato criarContrato();

	public void salvarContrato(Contrato contrato);

	/**
	 * Salva contrato, definindo sua situação como Aguardando Coleta.
	 * @param contrato a ser salvo.
	 */
	public void salvarContratoAguardandoColeta(Contrato contrato);

	public Contrato recuperaContratoPorId(Integer id);
	
	/**
	 * Recupera contrato e as cargas associadas a ele.
	 * @param contrato
	 * @return Contrato com lista de cargas
	 */
	public Contrato recuperaContratoComCargas(Contrato contrato);
	
	/**
	 * Recupera contratos em situação Aguardando Coleta, Aberto, Aguardando Entrega de um contratante.
	 * @param contratante
	 * @return Lista de Contratos
	 */
	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante);
	
	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante);

}
