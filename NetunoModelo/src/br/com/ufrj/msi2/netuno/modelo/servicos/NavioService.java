package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

@Local
public interface NavioService extends Serializable {

	public abstract List<Navio> filtrar(CriteriaQuery<Navio> consulta);
	
	public CriteriaBuilder getCriteriaBuilder();

	public void salvar(Navio navio);
	
	public Navio obterPorId(Integer idNavio);
	
	public List<Navio> recuperaTodos();
}
