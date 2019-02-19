package webcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import automation.utils.SeleniumUtils;

public class TextEditor extends Component {
	public TextEditor(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void cleanType() {
		bind();

		if (createWebElement()) {

			if (getValue() != null) {
				webElement.clear();
				webElement.sendKeys(getValue().toString());
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
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public String getText(String jsValueQuery) {
		js = (JavascriptExecutor) getDriver();
		Object result = js.executeScript(jsValueQuery);
		if (result != null) {
			return (String) result;
		} else {
			return null;
		}
	}

	public void executeJsScripts() {
		js = (JavascriptExecutor) getDriver();
		for (String command : jsComands) {
			js.executeScript(command);
		}
	}

	public void type(String value) {
		if (createWebElement()) {

			if (value != null) {
				SeleniumUtils.preencherCampo(elementAddress, value, getDriver());
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	@Override
	public String getRegisteredValue() {
		return webElement.getAttribute("value");
	}

	public void executeScript(String string) {
		// TODO Auto-generated method stub

	}

}
