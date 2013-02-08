package br.com.ufrj.msi2.netuno.modelo.servicos;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {
	private static final long serialVersionUID = -7087952795393582189L;

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Usuario recuperarUsuario(String login, String senha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario recuperarUsuario(Integer idUsuario) {
		// Recupera o usuário pela chave primária.
		return em.find(Usuario.class, idUsuario);
	}

}
