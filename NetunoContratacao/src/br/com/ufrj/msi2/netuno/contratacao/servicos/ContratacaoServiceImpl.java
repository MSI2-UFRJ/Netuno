package br.com.ufrj.msi2.netuno.contratacao.servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.Contratante;
import br.com.ufrj.msi2.netuno.modelo.entidades.Contrato;
import br.com.ufrj.msi2.netuno.modelo.servicos.ContratoService;

@Stateless
public class ContratacaoServiceImpl implements ContratacaoService {
	private static final long serialVersionUID = -7087952795393582189L;

	@EJB
	ContratoService contratoService;

	public List<Contrato> recuperaContratosPorContratante(Contratante contratante) {
		return contratoService.recuperaContratosPorContratante(contratante);
	}

	public ContratoService getContratoService() {
		return contratoService;
	}

	public void setContratoService(ContratoService contratoService) {
		this.contratoService = contratoService;
	}

}
