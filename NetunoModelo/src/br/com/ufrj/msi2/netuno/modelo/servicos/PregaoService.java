package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;

import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@Local
public interface PregaoService extends Serializable{

	public List<Pregao> recuperaPregoesAbertos();
	public List<Carga> recuperaCargasSemPregao();
	public void salvarPregao(Pregao p);
}
