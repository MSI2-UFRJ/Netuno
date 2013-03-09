package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;

@Local
public interface GerenciarCargasService extends Serializable {
	public List<Carga> listaCargasAgente(AgenteCarga agente);
}
