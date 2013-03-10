package br.com.ufrj.msi2.netuno.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteLogistica;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@ManagedBean(name="abrirPregoesModel")
@ViewScoped
public class AbrirPregoesModelBean extends MBean {

	private AgenteLogistica agentelogistica;
	
	private List<Carga> cargasSemPregao;
	
	private Pregao pregaoParaInserir;
	
	public Pregao getPregaoParaInserir() {
		return pregaoParaInserir;
	}

	public void setPregaoParaInserir(Pregao pregaoParaInserir) {
		this.pregaoParaInserir = pregaoParaInserir;
	}

	public AgenteLogistica getAgentelogistica() {
		return agentelogistica;
	}

	public void setAgentelogistica(AgenteLogistica agentelogistica) {
		this.agentelogistica = agentelogistica;
	}
	
	public List<Carga> getCargasSemPregao() {
		return cargasSemPregao;
	}

	public void setCargasSemPregao(List<Carga> cargasSemPregao) {
		this.cargasSemPregao = cargasSemPregao;
	}
}
