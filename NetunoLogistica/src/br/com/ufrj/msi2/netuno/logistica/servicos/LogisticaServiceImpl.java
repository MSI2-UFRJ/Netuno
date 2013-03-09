package br.com.ufrj.msi2.netuno.logistica.servicos;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;
import br.com.ufrj.msi2.netuno.modelo.servicos.PregaoService;

@Stateless
public class LogisticaServiceImpl implements LogisticaService{

	private static final long serialVersionUID = 4487980598535994706L;

	@EJB
	PregaoService pregaoService;
	
	public PregaoService getPregaoService() {
		return pregaoService;
	}

	public void setPregaoService(PregaoService pregaoService) {
		this.pregaoService = pregaoService;
	}

	public List<Pregao> recuperaPregoesAbertos() {
		return pregaoService.recuperaPregoesAbertos();
	}

}
