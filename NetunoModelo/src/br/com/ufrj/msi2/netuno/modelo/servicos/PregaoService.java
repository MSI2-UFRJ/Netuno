package br.com.ufrj.msi2.netuno.modelo.servicos;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Local;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@Local
public interface PregaoService extends Serializable{

	public List<Pregao> recuperaPregoesAbertos();
}
