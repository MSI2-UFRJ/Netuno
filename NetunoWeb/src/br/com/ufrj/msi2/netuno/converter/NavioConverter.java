package br.com.ufrj.msi2.netuno.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.ufrj.msi2.netuno.modelo.entidades.Navio;

public class NavioConverter implements Converter {
	
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		if (value != null) {
			return this.getAttributesFrom(component).get(value);
		}
		return null;
	}
	
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		if (value != null
				&& !"".equals(value)) {

			Navio entity = (Navio) value;

			this.addAttribute(component, entity);

			int codigo = entity.getId();
			return String.valueOf(codigo);
		}

		return (String) value;
	}

	protected void addAttribute(UIComponent component, Navio p) {
		String key = String.valueOf(p.getId());
		this.getAttributesFrom(component).put(key, p);
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}