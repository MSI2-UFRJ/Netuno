package br.com.ufrj.msi2.netuno.modelo.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

// O SQL Abaixo n�o � SQL :P � JPQL (Linguagem SQL do JPA)
@NamedQueries(
	{
		@NamedQuery(name="Usuario.recuperarPorLoginESenha",
					query="SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha"
		)
	}
)
@Entity
@Table(name="usuario")
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
	
	@Column(name="nome")
	private String nome;
	
	@Embedded
	private CPF cpf;

	public Integer getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public String getNome() {
		return nome;
	}

	public CPF getCpf() {
		return cpf;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(CPF cpf) {
		this.cpf = cpf;
	}

	public abstract String getTipo();

}
