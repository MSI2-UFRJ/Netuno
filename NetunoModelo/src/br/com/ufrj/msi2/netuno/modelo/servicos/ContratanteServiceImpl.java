package br.com.ufrj.msi2.netuno.modelo.servicos;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ufrj.msi2.netuno.modelo.entidades.CPF;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ResultadoNaoEncontradoException;
import br.com.ufrj.msi2.netuno.modelo.exceptions.ValidacaoException;

@Stateless
public class ContratanteServiceImpl implements ContratanteService {
	private static final long serialVersionUID = -7087952795393582189L;

	@PersistenceContext
	EntityManager em;
	
	@EJB
	UsuarioService usuarioService;
	
	public Contratante criarContratante() {
		return new Contratante();
	}
	
	public void salvarContratante(Contratante contratante) throws ValidacaoException {
		if(contratante.getLogin() == null) {
			throw new ValidacaoException("contratante.loginObrigatorio");
		}

		if(contratante.getLogin().trim().equals("")) {
			throw new ValidacaoException("contratante.loginInvalido");
		}

		if(contratante.getSenha() == null) {
			throw new ValidacaoException("contratante.senhaObrigatoria");
		}

		if(contratante.getSenha().trim().equals("")) {
			throw new ValidacaoException("contratante.senhaInvalida");
		}

		if(contratante.getNome() == null) {
			throw new ValidacaoException("contratante.nomeObrigatorio");
		}

		if(contratante.getLogin().trim().equals("")) {
			throw new ValidacaoException("contratante.nomeInvalido");
		}

		if(contratante.getEmail() == null) {
			throw new ValidacaoException("contratante.emailObrigatorio");
		}

		if(contratante.getEmail().trim().equals("")) {
			throw new ValidacaoException("contratante.emailInvalido");
		}

		if(contratante.getCpf() == null) {
			throw new ValidacaoException("contratante.cpfObrigatorio");
		}

		if(contratante.getCpf().getCpf().trim().equals("")) {
			throw new ValidacaoException("contratante.cpfInvalido");
		}

		em.persist(contratante);
	}
	
	public Contratante recuperaPorIdComContratos(Integer id) {
		Query query = em.createNamedQuery("Contratante.recuperaPorIdComFetch");
		query.setParameter("id", id);
		return (Contratante) query.getSingleResult();
	}
	
	@Override
	public Contratante recuperaPorCPF(CPF cpf) throws ResultadoNaoEncontradoException {
		Query query = em.createNamedQuery("Contratante.recuperaPorCPF");
		query.setParameter("cpf", cpf.getCpf());
		
		try {
			return (Contratante) query.getSingleResult();
		} catch (NoResultException e) {
			throw new ResultadoNaoEncontradoException(e.getMessage());
		}
	}

	public EntityManager getEm() {
		return em;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
