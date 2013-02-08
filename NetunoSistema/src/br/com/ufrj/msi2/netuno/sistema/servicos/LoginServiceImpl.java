package br.com.ufrj.msi2.netuno.sistema.servicos;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;
import br.com.ufrj.msi2.netuno.modelo.servicos.UsuarioService;
import br.com.ufrj.msi2.netuno.sistema.servicos.exception.NaoAutenticadoException;

@Stateless
public class LoginServiceImpl implements LoginService {
	private static final long serialVersionUID = 4816940844008395552L;
	
	@EJB
	UsuarioService usuarioService;
	
	@Override
	public Usuario autenticaUsuario(String login, String senha)
			throws NaoAutenticadoException {
		
		Usuario u = usuarioService.recuperarUsuario(login, senha);
		
		if(u == null) {
			throw new NaoAutenticadoException();
		}
		
		return u;
	}
	
}
