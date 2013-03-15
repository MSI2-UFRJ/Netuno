package br.com.ufrj.msi2.netuno.modelo.test;

import java.util.List;

import javax.persistence.Query;

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

	private static final CSVInitialDataSet<Porto> CSV_SET = 
            new CSVInitialDataSet<Porto>(
                    Porto.class, "porto.csv", 
                    "localizacao", "nome");
	
	public PortoServiceImplTest() {
		super(PortoServiceImpl.class, usedBeans, CSV_SET);
	}
	
	//Teste para verificar se a persistência funciona corretamente.
	@SuppressWarnings("unchecked")
	public void testeRecuperacaoDeCargas() {
		final PortoServiceImpl service = this.getBeanToTest();
		Query q = service.getEm().createQuery("from Porto order by id");
		assertEquals("RJ", ((Porto)q.getResultList().get(0)).getLocalizacao());
		assertEquals("SP", ((Porto)q.getResultList().get(1)).getLocalizacao());
		assertEquals(5, ((List<Porto>)q.getResultList()).size());
	}
	
	@Override
	public void setUp() throws Exception {
		super.setUp();
		// this.tx = this.getEntityManager().getTransaction();
		// tx.begin();
	}
}
