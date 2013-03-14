package br.com.ufrj.msi2.netuno.validator;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("pesoCargaValidator")
public class PesoCargaValidator implements Validator {
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if(value != null) {
			String valor = value.toString();
			
			double doubleValue = Double.parseDouble(valor);
			
			if(doubleValue == 0.0) {
				ResourceBundle bundle = context.getApplication().getResourceBundle(context, "contratacaoMsg");
				
				FacesMessage msg = new FacesMessage(bundle.getString("pesoCargaValidator.erro"));
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
		}
	}
	
}
