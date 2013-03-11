package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@Local
public interface ContratoService extends Serializable {
	
	public Contrato criarContrato();
	public void salvarContrato(Contrato contrato);
	public Contrato recuperaContratoPorId(Integer id);
	public Contrato recuperaContratoComCargas(Contrato contrato);
	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante);
	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante);

}
