package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Local
public interface PortoService extends Serializable {

	public abstract List<Porto> obterTodos();
	public abstract Porto obterPorId(Integer idPorto);
	public abstract void salvar(Porto porto) throws Exception;
	public abstract void alterar(Porto porto) throws Exception;
	public abstract void excluir(Integer idPorto) throws Exception;
	public abstract List<Porto> filtrar(Porto porto);
}
