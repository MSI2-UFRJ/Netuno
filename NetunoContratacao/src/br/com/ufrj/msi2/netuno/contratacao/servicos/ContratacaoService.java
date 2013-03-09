package br.com.ufrj.msi2.netuno.contratacao.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;

@Local
public interface ContratacaoService extends Serializable {

	public Contrato criarContrato();
	public List<Contrato> recuperaContratosAbertosPorContratante(Contratante contratante);
	public List<Contrato> recuperaContratosFechadosPorContratante(Contratante contratante);
	public void estimarDataEntrega(Contrato contrato, boolean temColeta, boolean temEntrega);

}
