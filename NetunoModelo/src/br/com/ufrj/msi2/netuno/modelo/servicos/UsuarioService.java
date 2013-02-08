package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;

@Local
public interface UsuarioService extends Serializable {
	public Usuario recuperarUsuario(String login, String senha);
	public Usuario recuperarUsuario(Integer idUsuario);
}
