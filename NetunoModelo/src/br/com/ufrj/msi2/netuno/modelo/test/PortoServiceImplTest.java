package br.com.ufrj.msi2.netuno.modelo.test;

import java.util.List;

import javax.persistence.EntityTransaction;

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
import br.com.ufrj.msi2.netuno.modelo.servicos.PortoServiceImpl;

import com.bm.testsuite.BaseSessionBeanFixture;
import com.bm.testsuite.dataloader.CSVInitialDataSet;

public class PortoServiceImplTest  extends BaseSessionBeanFixture<PortoServiceImpl> {
	
	private static final Class<?>[] usedBeans = { AgenteCarga.class, AgenteLogistica.class, AgentePorto.class, AgenteRota.class, Atraque.class, Carga.class, CargaComponente.class, CargaLog.class, Conteiner.class, Contratante.class, Contrato.class, EmpresaTransporte.class, Navio.class, ParteCarga.class, Patio.class, Porto.class, Pregao.class, RepresEmpTrans.class, Requisicao.class, Slot.class, Usuario.class };

	PortoServiceImpl service;
	
	private static final CSVInitialDataSet<Porto> CSV_SET1 = 
            new CSVInitialDataSet<Porto>(
                    Porto.class, "porto.csv", 
                    "id", "localizacao", "nome");
	
	//Não precisa estar aqui nesse teste, só para fins ilustrativos.
	private static final CSVInitialDataSet<Patio> CSV_SET2 = 
            new CSVInitialDataSet<Patio>(
                    Patio.class, "patio.csv", 
                    "id", "porto");
	
	public PortoServiceImplTest() {
		super(PortoServiceImpl.class, usedBeans, CSV_SET1, CSV_SET2);
	}
	
	public void testObterTodos() {
		List<Porto> lista = service.obterTodos();
		assertEquals("BA", (lista.get(0)).getLocalizacao());
		assertEquals("ES", (lista.get(1)).getLocalizacao());
		assertEquals("RJ", (lista.get(2)).getLocalizacao());
		assertEquals("SP", (lista.get(3)).getLocalizacao());
		assertEquals("SV", (lista.get(4)).getLocalizacao());
		assertEquals(5, lista.size());
	}
	
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
	
	public void testeSalvar() {
		Porto porto = new Porto();
		
		porto.setLocalizacao("PA");
		porto.setNome("Pará");
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
			fail("Falha na persistência: " + e.getMessage());
		}
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals("PA", lista.get(0).getLocalizacao());
		
	}
	
	public void testeAlterar(){
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
			fail("Falha na persistência: " + e.getMessage());
		}
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals("RJ", lista.get(0).getLocalizacao());
		assertEquals("Rio de Janeiro", lista.get(0).getNome());
	}
	
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
			//TODO Não sei porque está caindo aqui.
			if(tx.isActive()) tx.rollback();
			fail("Falha na persistência: " + e.getMessage());
		}
		
		List<Porto> lista = service.filtrar(porto);
		assertEquals(0,lista.size());
	}
	
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
	public void setUp() throws Exception {
		super.setUp();
		service = this.getBeanToTest();
	}
}
