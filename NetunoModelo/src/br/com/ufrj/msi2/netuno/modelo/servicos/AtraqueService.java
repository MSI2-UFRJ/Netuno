package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Atraque;

@Local
public interface AtraqueService extends Serializable{
	
	public Atraque obterPorId(Integer idAtraque);
	public List<Atraque> obterAtraquesPorto(Integer idPorto);
	public List<Atraque> obterAtraquesNavio(Integer idNavio);
	public void incluir(Atraque atraque) throws Exception;
	public void alterar(Atraque atraque) throws Exception;
	public void excluir(Integer idAtraque) throws Exception;
	public List<Atraque> filtrar(Atraque atraque);
}
