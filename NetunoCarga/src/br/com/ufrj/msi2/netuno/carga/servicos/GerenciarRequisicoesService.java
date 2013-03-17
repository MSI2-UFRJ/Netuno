package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@Local
public interface GerenciarRequisicoesService extends Serializable{
	/**
	 * Retorna Todas as Requisições para Todos os Agentes de Carga.
	 */
	public List<Requisicao> recuperarTodos();
	
	/**
	 * Retorna Todas as Requisições para um Agente de Carga.
	 * @param agente AgenteCarga. Agente cuja requisições se deseja obter.
	 * @return List<Requisicao>. Requisições para o agente.
	 */
	public List<Requisicao> obterPorAgenteCarga(AgenteCarga agente);
	/**
	 * Retorna a requisição desejada.
	 * @param idRequisicao int. Id da requisição desejada.
	 * @return Requisicao. Requisição curjo id é passado por parâmetro.
	 */
	public Requisicao obterPorId(int idRequisicao);
}
