package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Local
public interface PortoService extends Serializable {

	public abstract List<Porto> obterTodos();
	public abstract Porto obterPorId(Integer idPorto);
	public abstract void salvar(Porto porto);
	public abstract void excluir(Integer idPorto);
	public abstract void alterar(Porto porto);
	public abstract List<Porto> filtrar(CriteriaQuery<Porto> consulta);
	public CriteriaBuilder getCriteriaBuilder();
}
