package br.com.ufrj.msi2.netuno.modelo.test;

import org.junit.After;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteLogistica;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgentePorto;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteRota;
import br.com.ufrj.msi2.netuno.modelo.entidades.Atraque;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaComponente;
import br.com.ufrj.msi2.netuno.modelo.entidades.CargaLog;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;
import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Patio;
import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;
import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;
import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;
import br.com.ufrj.msi2.netuno.modelo.entidades.Slot;
import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;
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
