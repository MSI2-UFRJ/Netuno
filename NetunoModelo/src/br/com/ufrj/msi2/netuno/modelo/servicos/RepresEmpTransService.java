package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;

@Local
public interface RepresEmpTransService extends Serializable{

	public void salvar(RepresEmpTrans repEmpTrans);
}
