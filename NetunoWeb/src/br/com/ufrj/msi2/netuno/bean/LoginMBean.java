package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.modelo.entidades.Usuario;
import br.com.ufrj.msi2.netuno.sistema.servicos.LoginService;
import br.com.ufrj.msi2.netuno.sistema.servicos.exception.NaoAutenticadoException;

// Nome do Bean que é referenciado tanto nas telas (xhtml) quanto
// no faces-config.xml (Configuração de navegação).
@ManagedBean(name="Login")
@RequestScoped
public class LoginMBean extends MBean {
	
	@EJB
	LoginService loginService;
	
	private String login, senha;
	
	@PostConstruct
	public void construct() {
	}
	
	public String autenticar() {
		if(! validarDados()) {
			// Nao faz a navegacao, fica na mesma página.
			return "";
		}
		
		try {
			Usuario usuario = loginService.autenticaUsuario(login, senha);
			// TODO Guardar o id do usuário na sessão.
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute(Attributes.SessionAttributes.LOGIN.toString(), usuario.getId());
		} catch (NaoAutenticadoException e) {
			sendMessage("lM", FacesMessage.SEVERITY_ERROR, "Erro no login", "Usuario e/ou Senha invalido(s) !");
			return "";
		}
		
		// token para navegar entre as páginas (Configurado no faces-config.xml)
		return "logado";
	}
	
	public boolean validarDados() {
		if(login == null || login.trim().isEmpty()) {
			sendMessage("lM", FacesMessage.SEVERITY_WARN, "Erro no login", "Preencha o login");
			return false;
		} else if(senha == null || senha.trim().isEmpty()) {
			sendMessage("lM", FacesMessage.SEVERITY_WARN, "Erro no login", "Preencha a senha");
			return false;
		}
		
		return true;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}
