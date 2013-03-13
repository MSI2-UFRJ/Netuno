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
	public List<Conteiner> listaConteinersDisponiveis(Carga carga,Porto porto);
	public void criarNovoConteiner(Porto porto);
	public void AtualizaPeso(Conteiner conteiner, double novoPeso);
	public Conteiner obterPorId(Integer id);
	public List<Conteiner> recuperarTodos();
	public List<Conteiner> recuperarConteinersPorNavio(Navio navio);
}
