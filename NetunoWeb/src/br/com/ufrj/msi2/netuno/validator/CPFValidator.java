package br.com.ufrj.msi2.netuno.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("cpfValidator")
public class CPFValidator implements Validator {
	
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		boolean cpfValido = true;
		
		if(value == null) {
			cpfValido = false;
		} else {
			String cpf = String.valueOf(value);
			
			if (cpf == null || cpf.length() != 11) {
				cpfValido = false;
			} else {
				try {
					Long.parseLong(cpf);
				} catch (NumberFormatException e) {
					cpfValido = false;
				}
			}
		}
		
		if(!cpfValido) {
			FacesMessage msg = new FacesMessage("CPF inválido");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
	
}
