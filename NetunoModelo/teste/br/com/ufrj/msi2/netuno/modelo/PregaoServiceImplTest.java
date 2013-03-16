package br.com.ufrj.msi2.netuno.modelo;

import java.util.List;

import org.junit.Test;

import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;
import br.com.ufrj.msi2.netuno.modelo.servicos.PregaoServiceImpl;
import br.com.ufrj.msi2.netuno.modelo.support.BeanFinder;

import com.bm.testsuite.BaseSessionBeanFixture;
import com.bm.testsuite.dataloader.CSVInitialDataSet;

public class PregaoServiceImplTest  extends BaseSessionBeanFixture<PregaoServiceImpl> {
	
	private static final Class<?>[] usedBeans = BeanFinder.findBeans(BeanFinder.NETUNO_MODEL_PACKAGE);

	PregaoServiceImpl service;

	private static final CSVInitialDataSet<Pregao> CSV_SET = 
            new CSVInitialDataSet<Pregao>(
                    Pregao.class, "pregao.csv", 
                    "id", "abertoPor", "anuncia");
					//"id", "abertoPor_id", "anuncia_id");

	public PregaoServiceImplTest() {
		super(PregaoServiceImpl.class, usedBeans, CSV_SET);
	}
	
	@Test
	public void testRecuperaPregoesAbertos() {
		List<Pregao> lista = service.recuperaPregoesAbertos();
		assertEquals("BA", (lista.get(0)).getAnuncia().getDescricao());
	}
	
		/*EntityTransaction tx = this.getEntityManager().getTransaction();
		try {
			tx.begin();
			service.salvar(porto);
			tx.commit();
		} catch (Exception e) {
			if(tx.isActive()) tx.rollback();
			fail("Falha na persist�ncia: " + e.getMessage());
		}
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals("PA", lista.get(0).getLocalizacao());
		*/
	
	@Override
	public void setUp() throws Exception{
		super.setUp();
		service = this.getBeanToTest();
	}
}
