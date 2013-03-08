package br.com.ufrj.msi2.netuno.modelo.test;

import org.junit.After;

import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;
import br.com.ufrj.msi2.netuno.modelo.servicos.UsuarioServiceImpl;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ContratoServiceImplTest extends BaseSessionBeanFixture<UsuarioServiceImpl> {

	private static final Class<?>[] usedBeans = { Usuario.class };

	//EntityTransaction tx;

	/**
	 * Constructor.
	 */
	public ContratoServiceImplTest() {
		super(UsuarioServiceImpl.class, usedBeans);
	}

	/**
	 * Test the dependency injection.
	 */
	public void testDependencyInjection() {
		final UsuarioServiceImpl service = this.getBeanToTest();
		assertNotNull(service);
		assertNotNull(service.getEm());
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		// this.tx = this.getEntityManager().getTransaction();
		// tx.begin();
	}

	@After
	public void tearDown() throws Exception {
		// tx.commit();
	}

}
