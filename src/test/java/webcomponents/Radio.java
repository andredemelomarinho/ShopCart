package webcomponents;

import org.openqa.selenium.By;


import automation.utils.SeleniumUtils;
import webcomponents.Component;

public class Radio extends Component {
	public Radio(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void select() {
		if (createWebElement()) {

			if (!webElement.isSelected()) {
				webElement.click();
//				LogUtils.action("	- Check at :" + elementAddress.toString());
			}

			if (loaderPanel) {
				SeleniumUtils.checkLoaderPanel(getDriver());
			}
		}
	}

}
