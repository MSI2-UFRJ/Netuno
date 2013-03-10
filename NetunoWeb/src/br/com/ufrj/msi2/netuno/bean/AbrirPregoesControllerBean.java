package br.com.ufrj.msi2.netuno.bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;
import br.com.ufrj.msi2.netuno.logistica.servicos.LogisticaService;
import br.com.ufrj.msi2.netuno.modelo.entidades.AgenteLogistica;
import br.com.ufrj.msi2.netuno.modelo.entidades.Carga;
import br.com.ufrj.msi2.netuno.modelo.entidades.Pregao;

@ManagedBean(name="abrirPregoesController")
@ViewScoped
public class AbrirPregoesControllerBean {

	@EJB
	LogisticaService logisticaService;
	
	@ManagedProperty(value="#{abrirPregoesModel}")
	AbrirPregoesModelBean abrirPregoesModelBean;
	
	@PostConstruct
	public void construct() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		AgenteLogistica agentelogistica = (AgenteLogistica) session.getAttribute(Attributes.SessionAttributes.LOGIN.toString());
		if(agentelogistica != null) abrirPregoesModelBean.setAgentelogistica(agentelogistica);
		abrirPregoesModelBean.setCargasSemPregao(logisticaService.getCargasSemPregao());
	}
	
	public AbrirPregoesModelBean getAbrirPregoesModelBean() {
		return abrirPregoesModelBean;
	}

	public void setAbrirPregoesModelBean(AbrirPregoesModelBean abrirPregoesModelBean) {
		this.abrirPregoesModelBean = abrirPregoesModelBean;
	}
	
	public void inserirPregao(Carga carga){
		Pregao pregaoParaInserir = new Pregao();
		pregaoParaInserir.setAnuncia(carga);
		AgenteLogistica agenteLogistica = abrirPregoesModelBean.getAgentelogistica();
		if(agenteLogistica != null) pregaoParaInserir.setAbertoPor(agenteLogistica);
		else return; //EXIBIR ERRO POIS NAO ESTA LOGADO!!!
		logisticaService.salvarPregao(pregaoParaInserir);
		abrirPregoesModelBean.setCargasSemPregao(logisticaService.getCargasSemPregao());
	}

}
