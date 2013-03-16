package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

@Local
public interface ConteinerService extends Serializable {
	public abstract List<Conteiner> filtrar(CriteriaQuery<Conteiner> consulta);

	public CriteriaBuilder getCriteriaBuilder();

	public void salvarConteiner(Conteiner conteiner);
	
	public Conteiner obterPorId(Integer idConteiner);
	
	public List<Conteiner> recuperarConteinersPorNavio(Navio navio);
	
	public List<Conteiner> recuperarPorNavioPorAgenteParaDesembarque(Navio navio, AgenteCarga agente);
	
	public List<Conteiner> recuperarTodos();
}
