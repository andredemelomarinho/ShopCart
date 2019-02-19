package webcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import automation.utils.SeleniumUtils;
import webcomponents.Component;

public class TextBox  extends Component{


	public TextBox(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void cleanType() {
		bind();
		

		if (createWebElement()) {

			if (getValue() != null) {
				webElement.clear();
				webElement.sendKeys(getValue().toString());
//				LogUtils.action("	- Set value [" + getValue() + "] at :" + elementAddress.toString());
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void type() {
		bind();

		if (createWebElement()) {

			if (getValue() != null) {
				Actions actions = new Actions(getDriver());
				actions.sendKeys(webElement, getValue().toString()).perform();
//				LogUtils.action("	- Set value [" + getValue() + "] at :" + elementAddress.toString());
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void typeJavaScript() {
		String val = "";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		bind();

		if (getValue() != null) {
			val = getValue().toString();
		}

		js.executeScript("document.getElementById('" + getElementId() + "').value = '" + val + "'");
		onChange();

//		LogUtils.action("	- Set value [" + getValue() + "] at :" + elementAddress.toString());

		if (loaderPanel) {
			SeleniumUtils.checkLoaderPanel(getDriver());
		}
	}

	public void typeSimpleJavaScript() {
		String val = "";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		bind();

		if (getValue() != null) {
			val = getValue().toString();
		}

		js.executeScript("document.getElementById('" + getElementId() + "').value = '" + val + "'");
		// onChange();
//		LogUtils.action("	- Set value [" + getValue() + "] at :" + elementAddress.toString());

		if (loaderPanel) {
			SeleniumUtils.checkLoaderPanel(getDriver());
		}
	}

	public void typeJavaScript(String val) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		if (getValue() != null) {
			val = getValue().toString();
		}

		js.executeScript("document.getElementById('" + getElementId() + "').value = '" + val + "'");
		onChange();

//		LogUtils.action("	- Set value [" + getValue() + "] at :" + elementAddress.toString());

		if (loaderPanel) {
			SeleniumUtils.checkLoaderPanel(getDriver());
		}
	}
	public void typeJavaScriptAlterAttribute(String type) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		
		js.executeScript("var att = document.getElementByType('" + type + "')"); 
		js.executeScript("att.setAttribute('class,'' );");
		if (loaderPanel) {
			SeleniumUtils.checkLoaderPanel(getDriver());
		}
	}
	
	public void type(String value) {
		if (createWebElement()) {

			if (value != null) {
				webElement.clear();
				webElement.sendKeys(value);
//				LogUtils.action("	- Set value [" + value + "] at :" + elementAddress.toString());
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	/**
	 * Simula o botao ENTER a ser apertado no teclado
	 */
	public void pressEnter() {
		if (createWebElement()) {

			webElement.sendKeys(Keys.ENTER);

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}
	
	/**
	 * Simula o botao TAB a ser apertado no teclado
	 */
	
	public void pressTab(){
		if (createWebElement()){
			
			webElement.sendKeys(Keys.TAB);
			
			if (loaderPanel){
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}
	
	public void pressBackspace(){
		if (createWebElement()){
			
			webElement.sendKeys(Keys.BACK_SPACE);
			
			if (loaderPanel){
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}
	
	

	@Override
	public String getRegisteredValue() {
		return webElement.getAttribute("value");

}
}
