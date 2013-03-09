package br.com.ufrj.msi2.netuno.logistica.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.EmpresaTransporte;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;
import br.com.ufrj.msi2.netuno.modelo.entidades.RepresEmpTrans;


@Local
public interface LogisticaService extends Serializable {

		public List<Pregao> recuperaPregoesAbertos();
		public void salvarRepresEmpTrans(RepresEmpTrans repEmpTrans);
		public void salvarEmpTrans(EmpresaTransporte empTrans);
}
