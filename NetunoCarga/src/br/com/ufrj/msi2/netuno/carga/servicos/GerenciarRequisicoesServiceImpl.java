package br.com.ufrj.msi2.netuno.carga.servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.Requisicao;
import br.com.ufrj.msi2.netuno.modelo.servicos.RequisicaoService;

@Stateless
public class GerenciarRequisicoesServiceImpl implements GerenciarRequisicoesService {
	private static final long serialVersionUID = -5110400066719004923L;
	
	@EJB
	RequisicaoService requisicaoService;
	
	@Override
	public List<Requisicao> recuperarTodos() {
		return requisicaoService.recuperarTodos();
	}

	@Override
	public Requisicao obterPorId(int idRequisicao) {
		return requisicaoService.obterPorId(idRequisicao);
	}

}
