package br.com.ufrj.msi2.netuno.sistema.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;
import br.com.ufrj.msi2.netuno.sistema.servicos.exception.NaoAutenticadoException;

@Local
public interface LoginService extends Serializable {
	public Usuario autenticaUsuario(String login, String senha) throws NaoAutenticadoException;
}
