package webcomponents;

import org.openqa.selenium.By;

import automation.utils.LogUtils;

public class TreeView extends Component {

	public TreeView(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public void clicarOpcao(String nomeOpcao) {
		Button opcao = new Button(By.xpath("//span[contains(text(), '" + nomeOpcao + "')]"), "", true);

		opcao.click();
		LogUtils.info("Clicado na opção " + nomeOpcao + " da TreeView");
	}

	public void expandirItem(String nomeOpcao) {
		//TODO: desenvolver
	}
}
