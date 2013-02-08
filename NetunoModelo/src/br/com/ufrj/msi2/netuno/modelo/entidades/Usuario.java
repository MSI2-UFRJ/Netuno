package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
// O SQL Abaixo não é SQL :P É JPQL (Linguagem SQL do JPA)
@NamedQueries(
	{
		@NamedQuery(name="Usuario.recuperarPorLoginESenha",
					query="SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha"
		)
	}
)
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String login, senha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
