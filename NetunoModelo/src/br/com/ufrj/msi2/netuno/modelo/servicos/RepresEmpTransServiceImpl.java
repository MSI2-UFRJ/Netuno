package br.com.ufrj.msi2.netuno.modelo.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;

@Stateless
public class RepresEmpTransServiceImpl implements RepresEmpTransService {

	private static final long serialVersionUID = -1761177894689786451L;

	@PersistenceContext
	EntityManager em;

	@Override
	public void salvar(RepresEmpTrans repEmpTrans) {
		em.persist(repEmpTrans);
		
	}

}
