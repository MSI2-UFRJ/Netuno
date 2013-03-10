package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;

@Local
public interface GerenciarConteinersService extends Serializable{
	public List<Conteiner> listaConteinersDisponiveis(Porto porto);
}
