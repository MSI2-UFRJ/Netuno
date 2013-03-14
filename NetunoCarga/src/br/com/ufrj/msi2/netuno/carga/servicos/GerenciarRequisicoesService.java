package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;

@Local
public interface GerenciarRequisicoesService extends Serializable{
	public List<Requisicao> recuperarTodos();
	public Requisicao obterPorId(int idRequisicao);
	
}
