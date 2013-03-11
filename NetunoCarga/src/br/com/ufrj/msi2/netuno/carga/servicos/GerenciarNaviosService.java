package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

@Local
public interface GerenciarNaviosService extends Serializable {
	public List<Navio> listaNaviosComCarga(AgenteCarga agente);
	public Navio obterPorId(Integer idNavio);
}
