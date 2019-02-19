package webcomponents;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import session.ThreadManager;
import automation.utils.PropertiesUtils;
import automation.utils.SeleniumUtils;

public class Component {

	protected JavascriptExecutor js;
	protected WebElement webElement;
	protected By elementAddress;
	protected boolean loaderPanel;

	private List<By> alternativeElementAddresses = new ArrayList<By>();
	private Object value;
	private Object dtoBind;
	private String propertyBind;
	private String valorRegistrado;

	protected List<String> jsComands = new ArrayList<String>();
	
	public Component(){
		
	}
	/**
	 * Verifica se ele possui elemento no DTO para ser preenchido
	 */
	protected void bind() {
		if (dtoBind != null && propertyBind != null && !propertyBind.isEmpty()) {
			try {
				value = new PropertyDescriptor(propertyBind, dtoBind.getClass()).getReadMethod().invoke(dtoBind);
			} catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException
					| IntrospectionException e) {
				e.printStackTrace();
			}
		}
	}

	protected boolean createWebElement() {
		try {
			getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			webElement = waitComponent(elementAddress);
			
	
			if (webElement == null && getAlternativeElementAddresses() != null){
				for (By address : getAlternativeElementAddresses()){
					webElement = waitComponent(address);
	
					if (webElement != null) {
						break;
					}
				}
			}
		} catch(Exception e) {
		}

		return webElement != null;
	}

	public WebElement fluentWait(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(getDriver()).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) { 
				return driver.findElement(locator);
			}
		});

		return foo;
	}; 

	/**
	 * Retorna o ID do componente
	 * 
	 * @return
	 */
	public String getElementId() {
		return elementAddress.toString().substring(elementAddress.toString().indexOf(':') + 2);
	}

	/**
	 * Metodo construtor
	 * @param elementAddress
	 * @param propertyBind
	 * @param loadPanel
	 */
	public Component(By elementAddress, String propertyBind, boolean loadPanel) {
		this.elementAddress = elementAddress;
		this.propertyBind = propertyBind;
		this.loaderPanel = loadPanel;
	}

	/**
	 * Clica no componente
	 */
	public void click() {
		if (createWebElement()) {
			webElement.click();

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}

//			LogUtils.action("	- Click at:" + webElement.toString());
		}
	}

	/**
	 * Verifica se o componente está visivel
	 * 
	 * @return
	 */
	public boolean visible() {
		createWebElement();
		if (webElement == null) 
			return false;
		return webElement.isDisplayed();
	}

	public void sendKeys(String value){
		getDriver().findElement(elementAddress).sendKeys(value);
		getDriver().findElement(elementAddress).submit();
	}
	
	public void sendKeysOnly(String value){
		getDriver().findElement(elementAddress).sendKeys(value);
		
	}

	public WebElement waitComponent(By address){
		WebElement element = null;
		try{
			WebDriverWait wait = new WebDriverWait(getDriver(), Long.parseLong(PropertiesUtils.getTestProperty("componentTimeOut")));

			element = wait.until(ExpectedConditions.visibilityOfElementLocated(address));
			if (element == null){
//				LogUtils.info(" ---- failed attempt to wait for the component to be visible: ["+address.toString()+"]");
			}
		}catch(Exception e){
//			LogUtils.info(" ---- failed attempt to wait for the component to be visible: ["+address.toString()+"]");
		}
		return element;
	}

	/**
	 * Verifica se o componente está habilitado
	 * 
	 * @return
	 */
	public boolean enable() {
		createWebElement();
		if (webElement == null)
			return false;
		return webElement.isEnabled();
	}

	/**
	 * Verifica se o componente está selecionado
	 * 
	 * @return
	 */
	public boolean selected() {
		createWebElement();
		if (webElement == null)
			return false;
		return webElement.isSelected();
	}

	/**
	 * Retorna valor do atributo
	 * 
	 * @return
	 */
	public String getValueAttribute() {
		createWebElement();
		if (webElement == null)
			return "";
		return webElement.getAttribute("value");
	}
	public String getValueAttributeByAtt(String att) {
		createWebElement();
		if (webElement == null)
			return "";
		return webElement.getAttribute(att);
	}

	/**
	 * Retorna o texto
	 * 
	 * @return
	 */
	public String getText() {
		createWebElement();
		if (webElement == null)
			return "";
		return webElement.getText();
	}

	/**
	 * Verifica se o componente executa o carregando da pagina
	 * 
	 * @return
	 */
	public boolean isLoaderPanel() {
		return loaderPanel;
	}

	public void setLoaderPanel(boolean loaderPanel) {
		this.loaderPanel = loaderPanel;
	}

	public void onChange() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript("document.getElementById('" + getElementId() + "').onchange();");
		} catch(Exception e) {
		}

		if (loaderPanel) {
			SeleniumUtils.checkLoaderPanel(getDriver());
		}
	}
	
	public void executeJsScripts() {
		js = (JavascriptExecutor) getDriver();
		for (String command : jsComands) {
			js.executeScript(command);
		}
	}

	/**
	 * Retorna o valor do componente
	 * 
	 * @return
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Preenche o componente com o valor passado
	 * 
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	public String getPropertyBind() {
		return propertyBind;
	}

	public void setPropertyBind(String propertyBind) {
		this.propertyBind = propertyBind;
	}

	public Object getDtoBind() {
		return dtoBind;
	}

	public void setDtoBind(Object dtoBind) {
		this.dtoBind = dtoBind;
	}

	public String getRegisteredValue() {
		return valorRegistrado;
	}

	protected WebDriver getDriver() {
		return ThreadManager.getSession().getDriver();
	}

	public WebElement getWebElement() {
		return webElement;
	}

	public void setWebElement(WebElement webElement) {
		this.webElement = webElement;
	}

	public List<By> getAlternativeElementAddresses() {
		return alternativeElementAddresses;
	}

	public void setAlternativeElementAddresses(List<By> alternativeElementAddresses) {
		this.alternativeElementAddresses = alternativeElementAddresses;
	}
	
	public List<String> getJsComands() {
		return jsComands;
	}

	public void setJsComands(List<String> jsComands) {
		this.jsComands = jsComands;
	}

}
