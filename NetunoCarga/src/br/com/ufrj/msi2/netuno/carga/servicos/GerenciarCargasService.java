package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;

@Local
public interface GerenciarCargasService extends Serializable {
	/**
	 * Lista as Cargas para serem embarcadas de um Agente.
	 * @param agente AgenteCarga. Agente cujas cargas se deseja listar.
	 * @return List<Carga>. Cargas para Embarque do Agente.
	 */
	public List<Carga> listaCargasParaEmbarque(AgenteCarga agente);
	
	/**
	 * Lista as Cargas para serem desembarcadas de um Agente.
	 * @param agente AgenteCarga. Agente cujas cargas se deseja listar.
	 * @return List<Carga>. Cargas para Desembarque do Agente.
	 */
	public List<Carga> listaCargasParaDesembarque(AgenteCarga agente);
	
	/**
	 * Lista as PartesCarga de uma Carga com as informações do Conteiner onde cada ParteCarga se encontra.
	 * @param carga Carga. Carga cujas partes se deseja listar.
	 * @return List<Carga>. Lista de PartesCarga da Carga desejada.
	 */
	public List<ParteCarga> listaParteCargasComConteiner(Carga carga);
	
	/**
	 * Retorna Carga passada por parâmetro.
	 * @param idCarga Integer. Id da Carga desejada.
	 * @return Carga. Carga cujo id é passado por parâmetro.
	 */
	public Carga obterPorId(Integer idCarga);
	
	/**
	 * Aloca Carga em um Conteiner.
	 * @param carga Carga. Carga que se deseja alocar.
	 * @param conteiner Conteiner. Conteiner onde a carga deve ser alocada.
	 */
	public void alocarCarga(Carga carga,Conteiner conteiner);
	
	/**
	 * Retira do Conteiner a ParteCarga desejada.
	 * @param parteId int. Id da ParteCarga a ser desalocada.
	 */
	public void desalocarParteCarga(int parteId);

	/**
	 * Retira do Conteiner todas as ParteCargas de uma Carga.
	 * @param cargaId int. Id da carga a ser desalocada.
	 */
	public void desalocarTodasPartes(int cargaId);
	
}
