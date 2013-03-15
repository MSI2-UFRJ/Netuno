package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.ufrj.msi2.netuno.modelo.entidades.*;

@Local
public interface ParteCargaService extends Serializable {

	public abstract List<ParteCarga> filtrar(CriteriaQuery<ParteCarga> consulta);
	
	public CriteriaBuilder getCriteriaBuilder();
	
	public void salvar(ParteCarga carga);
	
	public ParteCarga obterPorId(Integer idCarga);
	
	public List<ParteCarga> listaParteCargasDisponiveis(AgenteCarga agente);

}
