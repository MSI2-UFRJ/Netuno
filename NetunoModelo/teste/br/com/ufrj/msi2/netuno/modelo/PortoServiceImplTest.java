package br.com.ufrj.msi2.netuno.modelo;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.Test;

import br.com.ufrj.msi2.netuno.modelo.entidades.Porto;
import br.com.ufrj.msi2.netuno.modelo.servicos.PortoServiceImpl;
import br.com.ufrj.msi2.netuno.modelo.support.BeanFinder;

import com.bm.testsuite.BaseSessionBeanFixture;
import com.bm.testsuite.dataloader.CSVInitialDataSet;

public class PortoServiceImplTest  extends BaseSessionBeanFixture<PortoServiceImpl> {
	
	private static final Class<?>[] usedBeans = BeanFinder.findBeans(BeanFinder.NETUNO_MODEL_PACKAGE);
	
	PortoServiceImpl service;
	
	private static final CSVInitialDataSet<Porto> CSV_SET1 = 
            new CSVInitialDataSet<Porto>(
                    Porto.class, "porto.csv", 
                    "id", "localizacao", "nome");
	
	public PortoServiceImplTest() {
		super(PortoServiceImpl.class, usedBeans, CSV_SET1);
	}
	
	@Test
	public void testObterTodos() {
		List<Porto> lista = service.obterTodos();
		assertEquals("BA", (lista.get(0)).getLocalizacao());
		assertEquals("ES", (lista.get(1)).getLocalizacao());
		assertEquals("RJ", (lista.get(2)).getLocalizacao());
		assertEquals("SP", (lista.get(3)).getLocalizacao());
		assertEquals("SV", (lista.get(4)).getLocalizacao());
		assertEquals(5, lista.size());
	}
	
	@Test
	public void testObterPorId() {
		Porto porto;
		
		porto = service.obterPorId(1);
		assertEquals("RJ", porto.getLocalizacao());
		porto = service.obterPorId(2);
		assertEquals("SP", porto.getLocalizacao());
		porto = service.obterPorId(3);
		assertEquals("BA", porto.getLocalizacao());
		porto = service.obterPorId(4);
		assertEquals("SV", porto.getLocalizacao());
		porto = service.obterPorId(5);
		assertEquals("ES", porto.getLocalizacao());
	}
	
	@Test
	public void testSalvar() {
		Porto porto = new Porto();
		
		porto.setLocalizacao("PA");
		porto.setNome("Par�");
		porto.setAgentes(null);
		porto.setAtraques(null);
		porto.setPatios(null);
		porto.setSlots(null);
		
		EntityTransaction tx = this.getEntityManager().getTransaction();
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
		
	}
	
	@Test
	public void testAlterar(){
		Porto porto = new Porto();
		
		porto.setId(1);
		porto.setLocalizacao("RJ");
		porto.setNome("Rio de Janeiro");
		porto.setAgentes(null);
		porto.setAtraques(null);
		porto.setPatios(null);
		porto.setSlots(null);
		
		EntityTransaction tx = this.getEntityManager().getTransaction();
		try {
			tx.begin();
			service.alterar(porto);
			tx.commit();
		} catch (Exception e) {
			if(tx.isActive()) tx.rollback();
			fail("Falha na persist�ncia: " + e.getMessage());
		}
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals("RJ", lista.get(0).getLocalizacao());
		assertEquals("Rio de Janeiro", lista.get(0).getNome());
	}
	
	@Test
	public void testExcluir(){
		Porto porto = new Porto();
		
		porto.setId(1);
		porto.setLocalizacao("RJ");
		porto.setNome("Rio");
		porto.setAgentes(null);
		porto.setAtraques(null);
		porto.setPatios(null);
		porto.setSlots(null);
		
		EntityTransaction tx = this.getEntityManager().getTransaction();
		try {
			tx.begin();
			service.excluir(1);
			tx.commit();
		} catch (Exception e) {
			if(tx.isActive()) tx.rollback();
			fail("Falha na persist�ncia: " + e.getMessage());
		}
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals(0,lista.size());
	}
	
	@Test
	public void testFiltrar(){		
		Porto porto = new Porto();
		
		porto.setId(1);
		porto.setLocalizacao("RJ");
		porto.setNome("Rio");
		porto.setAgentes(null);
		porto.setAtraques(null);
		porto.setPatios(null);
		porto.setSlots(null);
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals("RJ", lista.get(0).getLocalizacao());
		assertEquals(1, lista.size());
	}
	
	@Override
	public void setUp() throws Exception{
		super.setUp();
		service = this.getBeanToTest();
	}
}
