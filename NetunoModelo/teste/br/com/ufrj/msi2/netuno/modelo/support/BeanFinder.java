package br.com.ufrj.msi2.netuno.modelo.support;

import java.util.Set;

import javax.persistence.Entity;

import org.reflections.Reflections;

public abstract class BeanFinder {
	
	public static final String NETUNO_MODEL_PACKAGE = "br.com.ufrj.msi2.netuno.modelo";
	
	public static Class<?>[] findBeans(String rootDir){
		Reflections reflections = new Reflections(rootDir);
		
		Set<Class<?>> beans = reflections.getTypesAnnotatedWith(Entity.class);
		return beans.toArray(new Class<?>[beans.size()]);
	}
	
	public static void main(String args[]) {
		for(Class<?> beanClass : findBeans(NETUNO_MODEL_PACKAGE)) {
			System.out.println(beanClass.getCanonicalName());
		}
	}
}
