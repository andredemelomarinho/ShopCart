package webcomponents;

import org.openqa.selenium.By;

import webcomponents.Component;

public class Label extends Component {
	public Label(){
		super();
	}
	
	public Label(By elementAddress,  String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	@Override
	public String getRegisteredValue() {
		if (createWebElement()){
			return webElement.getAttribute("value");
		}else{
			return null;
		}
	}

}
