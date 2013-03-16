package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Local
public interface GerenciarNaviosService extends Serializable {
	public List<Navio> listaNaviosComCarga(AgenteCarga agente);
	public Navio obterPorId(Integer idNavio);
	public List<Navio> recuperarNaviosAtracadosEmPorto(Porto porto);
	public List<Navio> recuperarNaviosPorAgenteAtracadosEmPorto(AgenteCarga agente);
}
