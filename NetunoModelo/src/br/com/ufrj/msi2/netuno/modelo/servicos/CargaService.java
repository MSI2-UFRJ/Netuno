package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.*;

@Local
public interface CargaService extends Serializable {

	/**
	 * Filtra as cargas a partir de uma query.
	 * @param  CriteriaQuery<Carga> consulta. Query a ser executada.
	 * @return List<Cargas>.
	 */
	public abstract List<Carga> filtrar(CriteriaQuery<Carga> consulta);
	
	public CriteriaBuilder getCriteriaBuilder();

	/**
	 * Salva uma Carga.
	 * @param carga Carga. Carga a ser salva.
	 */
	public void salvar(Carga carga);
	
	/**
	 * Retorna a carga desejada.
	 * @param  idCarga Integer. Id da Carga desejada.
	 * @return Carga.
	 */
	public Carga obterPorId(Integer idCarga);
}
