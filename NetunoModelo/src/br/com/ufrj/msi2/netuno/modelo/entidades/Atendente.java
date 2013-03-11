package br.com.ufrj.msi2.netuno.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Funcion�rio respons�vel por gerar contratos para clientes que ligam para o escrit�rio ou que v�o ao porto com as cargas a entregar.
 * @author Thiago, Paula
 *
 */
@Entity
@Table(name="atendente")
@PrimaryKeyJoinColumn(name="id")
public class Atendente extends Usuario {
	private static final long serialVersionUID = -837838116953606430L;
	
	public String getTipo() {
		return "atendente";
	}

}