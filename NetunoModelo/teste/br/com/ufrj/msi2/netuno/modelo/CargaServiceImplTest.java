package br.com.ufrj.msi2.netuno.modelo;

import br.com.ufrj.msi2.netuno.modelo.servicos.CargaServiceImpl;
import br.com.ufrj.msi2.netuno.modelo.support.BeanFinder;

import com.bm.testsuite.BaseSessionBeanFixture;

public class CargaServiceImplTest  extends BaseSessionBeanFixture<CargaServiceImpl> {

	private static final Class<?>[] usedBeans = BeanFinder.findBeans(BeanFinder.NETUNO_MODEL_PACKAGE);

	public CargaServiceImplTest() {
		super(CargaServiceImpl.class, usedBeans);
	}
	
	public void testDependencyInjection() {
		final CargaServiceImpl service = this.getBeanToTest();
		assertNotNull(service);
		assertNotNull(service.getEm());
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		// this.tx = this.getEntityManager().getTransaction();
		// tx.begin();
	}
}
