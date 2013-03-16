package br.com.ufrj.msi2.netuno.modelo.test;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.junit.Test;

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
import br.com.ufrj.msi2.netuno.modelo.servicos.PregaoServiceImpl;

import com.bm.testsuite.BaseSessionBeanFixture;
import com.bm.testsuite.dataloader.CSVInitialDataSet;

public class PregaoServiceImplTest  extends BaseSessionBeanFixture<PregaoServiceImpl> {
	
	private static final Class<?>[] usedBeans = { AgenteCarga.class, AgenteLogistica.class, AgentePorto.class, AgenteRota.class, Atraque.class, Carga.class, CargaComponente.class, CargaLog.class, Conteiner.class, Contratante.class, Contrato.class, EmpresaTransporte.class, Navio.class, ParteCarga.class, Patio.class, Porto.class, Pregao.class, RepresEmpTrans.class, Requisicao.class, Slot.class, Usuario.class };

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
			fail("Falha na persistência: " + e.getMessage());
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
