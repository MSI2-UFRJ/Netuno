package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.*;

@Local
public interface CargaService extends Serializable {
	
	public abstract List<Carga> filtrar(CriteriaQuery<Carga> consulta);
	public CriteriaBuilder getCriteriaBuilder();

}
