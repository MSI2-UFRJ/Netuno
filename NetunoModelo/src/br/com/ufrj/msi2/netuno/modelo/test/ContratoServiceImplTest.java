package br.com.ufrj.msi2.netuno.modelo.test;

import java.util.List;

import org.junit.After;

import br.com.ufrj.msi2.netuno.modelo.entidades.*;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoServiceImpl;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ContratoServiceImplTest extends BaseSessionBeanFixture<ContratoServiceImpl> {

	private static final Class<?>[] usedBeans = { AgenteCarga.class, AgenteLogistica.class, AgentePorto.class, AgenteRota.class, Atraque.class, Carga.class, CargaComponente.class, CargaLog.class, Conteiner.class, Contratante.class, Contrato.class, EmpresaTransporte.class, Navio.class, ParteCarga.class, Patio.class, Porto.class, Pregao.class, RepresEmpTrans.class, Requisicao.class, Slot.class, Usuario.class };

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
	
	public void testRecuperaContratosPorContratante() {
		final ContratoServiceImpl service = this.getBeanToTest();
		
		Contratante contratante = new Contratante();
		contratante.setId(1);
		
		List<Contrato> lista =  service.recuperaContratosPorContratante(contratante);
		
		System.out.println(lista);
		
		assertNotNull(lista);
		assertTrue(lista.size() >= 1);
	}

	@After
	public void tearDown() throws Exception {
		// tx.commit();
	}

}
