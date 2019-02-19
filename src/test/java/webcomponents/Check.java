package webcomponents;

import org.openqa.selenium.By;
import automation.utils.SeleniumUtils;
import webcomponents.Component;

public class Check extends Component {
	public Check(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void select() {
		bind();

		if (createWebElement()) {

			if (!webElement.isSelected()) {
				webElement.click();
				// LogUtils.action(" - Check at :" + elementAddress.toString());
			}

			if (loaderPanel) {
				// SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

	// TODO: verificar se esta funcionando onde e chamado. Teve que ser usado o
	// method click no teste pois não clicava ao usar esse metodo
	public void checkUncheckCheckbox() {
		bind();

		if (createWebElement()) {

			Boolean check = (Boolean) getValue();

			if (check != null) {
				if (check.equals(true)) {
					if (!webElement.isSelected()) {
						webElement.click();
						// LogUtils.action(" - Check at :" +
						// elementAddress.toString());
					}
				} else if (check.equals(false)) {
					if (webElement.isSelected()) {
						webElement.click();
						// LogUtils.action(" - Uncheck at :" +
						// elementAddress.toString());
					}
				}
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

}
