package br.com.ufrj.msi2.netuno.modelo;

import org.junit.After;

import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoServiceImpl;
import br.com.ufrj.msi2.netuno.modelo.support.BeanFinder;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ContratoServiceImplTest extends BaseSessionBeanFixture<ContratoServiceImpl> {

	private static final Class<?>[] usedBeans = BeanFinder.findBeans(BeanFinder.NETUNO_MODEL_PACKAGE);
	
	//EntityTransaction tx;

	/**
	 * Constructor.
	 */
	public ContratoServiceImplTest() {
		super(ContratoServiceImpl.class, usedBeans);
	}

	/**
	 * Test the dependency injection.
	 */
	public void testDependencyInjection() {
		final ContratoServiceImpl service = this.getBeanToTest();
		assertNotNull(service);
		assertNotNull(service.getEm());
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		// this.tx = this.getEntityManager().getTransaction();
		// tx.begin();
	}
	
	public void recuperaContratosAbertosPorContratante() {
//		final ContratoServiceImpl service = this.getBeanToTest();
//		
//		Contratante contratante = new Contratante();
//		contratante.setId(1);
//		
//		List<Contrato> lista =  service.recuperaContratosAbertosPorContratante(contratante);
//		
//		assertNotNull(lista);
//		assertTrue(lista.size() >= 1);
	}

	@After
	public void tearDown() throws Exception {
		// tx.commit();
	}

}
