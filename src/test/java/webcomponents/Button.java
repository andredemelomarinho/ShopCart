package webcomponents;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class Button extends Component{
	public Button(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public String displayedValue() {
		if (webElement == null)
			createWebElement();
		return webElement.getText();
	}

	public void clickHold(long duration) {
		if (createWebElement()) {
			Actions action = new Actions(getDriver());
			action.moveToElement(webElement).clickAndHold().perform();
			action.pause(duration);
			action.release(webElement).perform();
		}
	}

}
