package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Local
public interface NavioService extends Serializable {

	public abstract List<Navio> filtrar(CriteriaQuery<Navio> consulta);
	
	public CriteriaBuilder getCriteriaBuilder();

	public void salvar(Navio navio) throws Exception;
	
	public Navio obterPorId(Integer idNavio);
	
	public List<Navio> recuperaTodos();
	
	public void alterar(Navio navio) throws Exception;
	
	public void excluir(Integer idNavio) throws Exception;
	
	public List<Navio> filtrar(Navio navio);
	
	public List<Navio> recuperarNaviosAtracadosEmPorto(Porto porto);
	
	public List<Navio> recuperarNaviosPorAgenteAtracadosEmPorto(AgenteCarga agente);
}
