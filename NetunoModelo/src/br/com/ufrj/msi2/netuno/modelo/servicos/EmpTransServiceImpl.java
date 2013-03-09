package br.com.ufrj.msi2.netuno.modelo.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;
import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;

@Stateless
public class EmpTransServiceImpl implements EmpTransService{

	private static final long serialVersionUID = 1347143756706229298L;

	@PersistenceContext
	EntityManager em;

	@Override
	public void salvar(EmpresaTransporte empTrans) {
		for(RepresEmpTrans r : empTrans.getRepresentantes()){
			r.setRepresenta(empTrans);
		}
		em.persist(empTrans);
	}

}
