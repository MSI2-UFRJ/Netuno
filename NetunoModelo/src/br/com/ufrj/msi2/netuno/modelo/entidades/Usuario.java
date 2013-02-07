package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
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
	private Integer id;
	private String login, senha;
}
