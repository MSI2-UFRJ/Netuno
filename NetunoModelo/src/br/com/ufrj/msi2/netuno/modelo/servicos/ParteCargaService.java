package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.*;

@Local
public interface ParteCargaService extends Serializable {

	/**
	 * Filtra a lista de PartesCarga a partir da uma query de consulta.
	 * @param CriteriaQuery<ParteCarga> consulta. Query a executar
	 * @return List<ParteCarga>. Lista de ParteCargas filtrada
	 */
	public abstract List<ParteCarga> filtrar(CriteriaQuery<ParteCarga> consulta);
	
	public CriteriaBuilder getCriteriaBuilder();
	
	/**
	 * Salva uma ParteCarga.
	 * @param carga ParteCarga a ser salva.
	 */
	public void salvar(ParteCarga carga);
	
	/**
	 * Retorna a ParteCarga desejada
	 */
	public ParteCarga obterPorId(Integer idCarga);
	
	/**
	 * Retorna as ParteCargas de um Agente disponíveis para desembarque.
	 * @param agente Agente. 
	 * @return List<ParteCarga>.
	 */
	public List<ParteCarga> listaParteCargasDisponiveis(AgenteCarga agente);

}
