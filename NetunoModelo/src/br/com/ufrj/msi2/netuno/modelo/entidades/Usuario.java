package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// O SQL Abaixo não é SQL :P É JPQL (Linguagem SQL do JPA)
@NamedQueries(
	{
		@NamedQuery(name="Usuario.recuperarPorLoginESenha",
					query="SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha"
		)
	}
)

@Table(name="usuario")
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = -7388109837156922864L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	public Integer getId() {
		return id;
	}
	
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
