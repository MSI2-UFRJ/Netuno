package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;

@ManagedBean(name="verContratosModel")
@ViewScoped
public class VerContratosModelBean extends MBean {
	
	private Usuario usuario;
	
	@PostConstruct
	public void construct() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
