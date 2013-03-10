package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;

@Local
public interface ConteinerService extends Serializable {
	public abstract List<Conteiner> filtrar(CriteriaQuery<Conteiner> consulta);

	public CriteriaBuilder getCriteriaBuilder();

	public void salvarConteiner(Conteiner conteiner);
	
	public Conteiner obterPorId(Integer idConteiner);
}
