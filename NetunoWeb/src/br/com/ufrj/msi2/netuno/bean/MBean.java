package br.com.ufrj.msi2.netuno.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MBean {
	public void sendMessage(String idComponenteMensagem, Severity severity, String sumario, String descricao) {
		FacesMessage message = new FacesMessage(severity, sumario, descricao);
		
		FacesContext.getCurrentInstance().addMessage(idComponenteMensagem, message);
	}
}
