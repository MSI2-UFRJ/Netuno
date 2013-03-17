package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Local
public interface GerenciarConteinersService extends Serializable{
	/**
	 * Lista os Conteiners de um Porto que t�m espa�o para aloca��o de uma Carga.
	 * @param porto Porto. Porto onde de onde os conteiners ser�o listados.
	 * @return . List<Conteiner>. Lista de conteiners dispon�vels para alca��o de carga.
	 */
	public List<Conteiner> listaConteinersDisponiveis(Carga carga,Porto porto);
	
	/**
	 * Cria um novo Conteiner no Posto desejado.
	 * @param porto Porto. Porto onde o Conteiner ser� listado.
	 */
	public void criarNovoConteiner(Porto porto);
	
	/**
	 * Atualiza o peso alocado em um Conteiner.
	 */
	public void AtualizaPeso(Conteiner conteiner, double novoPeso);
	
	/**
	 * Retorna um Conteiner a partir do seu Id.
	 */
	public Conteiner obterPorId(Integer id);
	
	/**
	 * Retorna uma lista com todos os Conteiners.
	 */
	public List<Conteiner> recuperarTodos();
	
	/**
	 * Retorna uma lista com todos os Conteiners de um Navio.
	 */
	public List<Conteiner> recuperarConteinersPorNavio(Navio navio);
	
	/**
	 * Retorna uma lista com todos os Conteiners de um Navio que t�m requisi��o de Desembarque para um Agente espec�fico.
	 */
	public List<Conteiner> recuperarPorNavioPorAgenteParaDesembarque(Navio navio, AgenteCarga agente);
}
