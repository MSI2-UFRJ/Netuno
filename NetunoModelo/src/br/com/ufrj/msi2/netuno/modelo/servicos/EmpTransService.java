package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;

@Local
public interface EmpTransService extends Serializable{

	public abstract void salvar(EmpresaTransporte empTrans);
}
