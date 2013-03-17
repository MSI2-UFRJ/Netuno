package br.com.ufrj.msi2.netuno.modelo;

import javax.persistence.EntityTransaction;

import org.junit.Test;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ValidacaoException;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratanteServiceImpl;
import br.com.ufrj.msi2.netuno.modelo.support.BeanFinder;

import com.bm.testsuite.BaseSessionBeanFixture;

public class ContratanteServiceImplTest extends BaseSessionBeanFixture<ContratanteServiceImpl> {

	private static final Class<?>[] usedBeans = BeanFinder.findBeans(BeanFinder.NETUNO_MODEL_PACKAGE);
	
	public ContratanteServiceImplTest() {
		super(ContratanteServiceImpl.class, usedBeans);
	}

	public void testDependencyInjection() {
		final ContratanteServiceImpl service = this.getBeanToTest();
		assertNotNull(service);
		assertNotNull(service.getEm());
	}
	
	/**
	 * Verifica se o método criarContratante cria um novo contratante, que tem que ter id nulo.
	 */
	@Test
	public void testCriarContratante() {
		ContratanteServiceImpl service = this.getBeanToTest();
		
		Contratante contratante = service.criarContratante();
		
		assertNotNull(contratante);
		assertNull(contratante.getId());
	}
	
	/**
	 * Salvar contratante novo. Contratante deve ser inserido.
	 */
	@Test
	public void testSalvarContratanteSucesso() {
		ContratanteServiceImpl service = this.getBeanToTest();
		
		Contratante contratante = service.criarContratante();
		CPF cpf = new CPF();
		
		contratante.setLogin("login");
		contratante.setSenha("senha");
		contratante.setNome("João");
		contratante.setEmail("a@b.com");
		cpf.setCpf("12345678910");
		contratante.setCpf(cpf);
		
		ValidacaoException exception = null;

		EntityTransaction tx = this.getEntityManager().getTransaction();
		tx.begin();
		try {
			service.salvarContratante(contratante);
			tx.commit();
		} catch (ValidacaoException e) {
			exception = e;
			if(tx.isActive()) {
				tx.rollback();
			}
		}
		
		assertNull(exception);
	}
	
	/**
	 * Salvar contratante novo sem nome. Contratante não deve ser inserido.
	 */
	@Test
	public void testSalvarContratanteSemNome() {
		ContratanteServiceImpl service = this.getBeanToTest();
		
		Contratante contratante = service.criarContratante();
		
		contratante.setLogin(null);
		
		ValidacaoException exception = null;
		
		try {
			service.salvarContratante(contratante);
		} catch (ValidacaoException e) {
			exception = e;
		}
		
		assertNotNull(exception);
	}
	
	/**
	 * Salvar contratante novo com senha composta somente de espaços em branco. Contratante não deve ser inserido.
	 */
	@Test
	public void testSalvarContratanteSenhaEmBranco() {
		ContratanteServiceImpl service = this.getBeanToTest();
		
		Contratante contratante = service.criarContratante();
		
		contratante.setLogin("login2");
		contratante.setSenha("   ");
		
		ValidacaoException exception = null;
		
		try {
			service.salvarContratante(contratante);
		} catch (ValidacaoException e) {
			exception = e;
		}
		
		assertNotNull(exception);
	}
	
	/**
	 * Recuperar contratante com seus contratos.
	 */
	@Test
	public void testRecuperarPorIdComContratos() {
		ContratanteServiceImpl service = this.getBeanToTest();
		
		Contratante contratante = service.recuperaPorIdComContratos(new Integer(1));
		
		System.out.println(contratante.getNome());
		System.out.println(contratante.getContratos());
		
		assertNotNull(contratante);
		assertNotNull(contratante.getContratos());
	}

}
