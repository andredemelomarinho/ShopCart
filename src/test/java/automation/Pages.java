package automation;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

public class Pages {

	private final WebDriver driver;
	private final Map<Class<?>, Object> extendedPages;
	
   public Pages(WebDriver driver){
		this.driver = driver;
		this.extendedPages = new HashMap<Class<?>, Object>();
	}

	@SuppressWarnings("unchecked")
	private <T> T criar(Class<T> clazz){
		T obj;
		try {
			obj = clazz.getConstructor(WebDriver.class).newInstance(driver);
			extendedPages.put(clazz, obj);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Não foi possível instanciar a página: "+clazz, e);
		}
		return (T) extendedPages.get(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz){
		if(extendedPages.containsKey(clazz)){
			return (T) extendedPages.get(clazz);
		}
		return (T) criar(clazz);
	}

	public WebDriver getDriver() {
		return driver;
	}

}
