package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@Local
public interface ContratoService extends Serializable {

	public List<Contrato> getContratosPorContratante(Contratante contratante);

}
