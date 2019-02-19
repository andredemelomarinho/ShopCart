package automation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import automation.utils.TestData;
import automation.utils.PageInitiater;
import webcomponents.Component;

public class AbstractPage {
	private TestData dto;
	private PageInitiater initiater = new PageInitiater();

  
//	private PageInitiater initiater = new PageInitiater();
//
//	public List<ValidationResult> validaEstadoComponentes(List<ValidaComponenteDTO> lstValidaComponente) {
//		List<ValidationResult> lstResult = new ArrayList<ValidationResult>();
//
//		for (ValidaComponenteDTO valida: lstValidaComponente){
//			lstResult.addAll(validaEstadoComponente(valida));
//		}
//		return lstResult;
//	}
//
//	public List<ValidationResult> validaEstadoComponente(ValidaComponenteDTO validaComponente) {
//		ComponentValidator validator = new ComponentValidator();
//		List<ValidationResult> lstResult = new ArrayList<ValidationResult>();

//		Component compo = getPageComponent(validaComponente.getComponent());
//		lstResult.addAll(validator.validateComponent(compo, validaComponente));
//		return lstResult;
	

	protected Component getPageComponent(String component){
		return null;
	}
	
	private void setupPage(Object dto){
		Field[] declaredFields = this.getClass().getDeclaredFields();
		for(int i =0; i<= declaredFields.length -1; i++){
			if (Component.class.isAssignableFrom(declaredFields[i].getType())){
				try {
					Object comp = new PropertyDescriptor(declaredFields[i].getName(), this.getClass()).getReadMethod().invoke(this);
					new PropertyDescriptor("dtoBind", Component.class).getWriteMethod().invoke(comp, dto);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
//				((Component)declaredFields[i]).setDtoBind(dto);
			}
		}
		
	}
	
	public List<Component> getPageComponents(){
		Field[] declaredFields = this.getClass().getDeclaredFields();
		List<Component> components = new ArrayList<Component>();
		for(int i =0; i<= declaredFields.length -1; i++){
			if (Component.class.isAssignableFrom(declaredFields[i].getType())){
				try {
					Object comp = new PropertyDescriptor(declaredFields[i].getName(), this.getClass()).getReadMethod().invoke(this);
					components.add((Component) comp);//new PropertyDescriptor("dtoBind", Component.class).getWriteMethod().invoke(comp, dto);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IntrospectionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				((Component)declaredFields[i]).setDtoBind(dto);
			}
		}
		return components;
		
	}
	
	protected void setupPage(){
		setupPage(dto);
	}

	public TestData getDto() {
		return dto;
	}

	public void setDto(TestData dto) {
		this.dto = dto;
	}
	
	public void setDtoBind(TestData dto) {
		this.dto = dto;
		setupPage();
	}
		
	public PageInitiater getInitiater() {
		return initiater;
	}

	public void setInitiater(PageInitiater initiater) {
		this.initiater = initiater;
	}

	
	
	

}
