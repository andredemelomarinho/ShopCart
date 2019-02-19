package webcomponents;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import automation.utils.SeleniumUtils;
import webcomponents.Component;

public class Combo extends Component {

	public Combo(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void select() {
		bind();
		if (createWebElement()) {
			if (getValue() != null) {
				Select select = new Select(webElement);
				select.selectByVisibleText(getValue().toString());
//				LogUtils.action("	- Select value [" + getValue() + "] at :" + elementAddress.toString());
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void select(String value) {
		if (createWebElement()) {

			if (value != null) {
				try {
					Select select = new Select(webElement);
					select.selectByVisibleText(value);
//					LogUtils.action("	- Select value [" + value + "] at :" + webElement.toString());
				} catch (NoSuchElementException e) {
//					LogUtils.error(e, "Elemento inexistente. Rever o valor passado como parâmetro");
				}
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void selectContainedValue(String value) {
		if (createWebElement()) {

			if (value != null) {
				try {
					Select select = new Select(webElement);
					select.selectByVisibleText(getContainedValue(value));
//					LogUtils.action("	- Select value [" + value + "] at :" + webElement.toString());
				} catch (NoSuchElementException e) {
//					LogUtils.error(e, "Elemento inexistente. Rever o valor passado como parâmetro");
				}
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void selectFirstElement() {
		bind();

		if (createWebElement()) {

			Select select = new Select(webElement);
			select.selectByIndex(0);
//			LogUtils.action("	- Select first value at :" + elementAddress.toString());

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void selectPreviousElement() {
		bind();

		if (createWebElement()) {

			Select select = new Select(webElement);
			List<WebElement> comboList = select.getOptions();

			int index = 0;
			for (WebElement option : comboList) {
				if (option.isSelected()) {
					break;
				}
				index++;
			}
			if (0 < index - 1) {
				select.selectByIndex(index - 1);
			}

//			LogUtils.action("	- Select previous value at :" + elementAddress.toString());
			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public void selectNextElement() {
		bind();

		if (createWebElement()) {

			Select select = new Select(webElement);
			List<WebElement> comboList = select.getOptions();

			int index = 0;
			for (WebElement option : comboList) {
				if (option.isSelected()) {
					break;
				}
				index++;
			}
			if (comboList.size() > index + 1) {
				select.selectByIndex(index + 1);
			}
//			LogUtils.action("	- Select next value at :" + elementAddress.toString());
			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	public String getSelectedValue() {
		bind();
		if (createWebElement()) {

			Select select = new Select(webElement);
			List<WebElement> comboList = select.getOptions();

			for (WebElement option : comboList) {
				if (option.isSelected()) {
					return option.getText();
				}
			}
		}

		return null;
	}

	public boolean findValue(String value) {
		bind();

		if (createWebElement()) {
			Select select = new Select(webElement);
			List<WebElement> comboList = select.getOptions();

			for (WebElement option : comboList) {
				if (value.equals(option.getText())) {
					return true;
				}
			}
		}

		return false;
	}

	public String getContainedValue(String value) {
		bind();

		if (createWebElement()) {
			Select select = new Select(webElement);
			List<WebElement> comboList = select.getOptions();

			for (WebElement option : comboList) {
				if (option.getText().contains(value)) {
					return option.getText();
				}
			}
		}

		return "";
	}

	@Override
	public String getRegisteredValue() {
		return getSelectedValue();
	}

}
