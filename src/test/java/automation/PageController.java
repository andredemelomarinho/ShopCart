package automation;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import webcomponents.Component;

public class PageController {

//	AbstractPage page;
//	private Paginas page;
	
	public static void setupPage(Object page, Object dto){
		Field[] declaredFields = page.getClass().getDeclaredFields();
		for(int i =0; i<= declaredFields.length -1; i++){
			if (Component.class.isAssignableFrom(declaredFields[i].getType())){
				try {
					Object comp = new PropertyDescriptor(declaredFields[i].getName(), page.getClass()).getReadMethod().invoke(page);
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
					e.printStackTrace();
				}
//				((Component)declaredFields[i]).setDtoBind(dto);
			}
		}
		
	}
	
}



