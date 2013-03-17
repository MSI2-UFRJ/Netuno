package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@Local
public interface GerenciarRequisicoesService extends Serializable{
	/**
	 * Retorna Todas as Requisi��es para Todos os Agentes de Carga.
	 */
	public List<Requisicao> recuperarTodos();
	
	/**
	 * Retorna Todas as Requisi��es para um Agente de Carga.
	 * @param agente AgenteCarga. Agente cuja requisi��es se deseja obter.
	 * @return List<Requisicao>. Requisi��es para o agente.
	 */
	public List<Requisicao> obterPorAgenteCarga(AgenteCarga agente);
	/**
	 * Retorna a requisi��o desejada.
	 * @param idRequisicao int. Id da requisi��o desejada.
	 * @return Requisicao. Requisi��o curjo id � passado por par�metro.
	 */
	public Requisicao obterPorId(int idRequisicao);
	
	/**
	 * Atende uma requisi��o que estava em aberto e a marca como atendida. 
	 * @param requisicao A requisi��o a ser atendida.
	 */
	public void atenderResquisicao(Requisicao requisicao);
}
