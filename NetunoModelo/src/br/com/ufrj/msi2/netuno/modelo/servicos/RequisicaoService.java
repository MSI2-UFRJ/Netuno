package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@Local
public interface RequisicaoService extends Serializable {

	public CriteriaBuilder getCriteriaBuilder();
	
	public abstract List<Requisicao> filtrar(CriteriaQuery<Requisicao> consulta);
	
	public Requisicao criarRequisicao();
	
	public void salvarRequisicao(Requisicao requisicao);
	
	public Requisicao obterPorId(Integer idRequisicao);
	
	public List<Requisicao> obterPorAgenteCarga(AgenteCarga agente);
	
	public List<Requisicao> recuperarTodos();
	
	public void atenderResquisicao(Requisicao requisicao);
	
}
