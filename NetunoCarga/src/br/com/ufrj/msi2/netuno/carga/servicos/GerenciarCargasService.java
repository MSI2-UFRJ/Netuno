package br.com.ufrj.msi2.netuno.carga.servicos;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteCarga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Conteiner;
import br.com.ufrj.msi2.netuno.modelo.entidades.ParteCarga;

@Local
public interface GerenciarCargasService extends Serializable {
	public List<Carga> listaCargasParaEmbarque(AgenteCarga agente);
	public List<Carga> listaCargasParaDesembarque(AgenteCarga agente);
	public List<ParteCarga> listaParteCargasComConteiner(Carga carga);
	public Carga obterPorId(Integer idCarga);
	
	public void alocarCarga(Carga carga,Conteiner conteiner);
	
	public void desalocarParteCarga(int parteId);

	public void desalocarTodasPartes(int cargaId);
	
}
