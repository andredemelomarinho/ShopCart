package webcomponents;

import org.openqa.selenium.By;

import automation.utils.PageListener;
import webcomponents.Component;
import automation.AbstractPage;
import webcomponents.Button;
import webcomponents.Label;

public class Window extends Component implements PageListener  {

	private Label labelTituloJanela = new Label(By.className("ui-dialog-title"), "", true);
	private Button botaoFechaJanela = new Button(By.className("ui-icon ui-icon-closethick"), "", true);

	public Window(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}
	
	

	public Window(String tituloJanela) {
		super(By.xpath("//div[div[@class='ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix']/span[contains(text(), '" + tituloJanela + "')]]//div[@class='ui-dialog-content ui-widget-content']/iframe"), "", true);
	}

	/**
	 * Retorna o titulo da janela em foco
	 * @return
	 */
	public String getTituloJanela() {
		return labelTituloJanela.getText();
	}

	/**
	 * Metodo para fechar a janela em foco
	 */
	public void clicarFecharJanela() {
		botaoFechaJanela.click();
		releaseFoco();
		
	}

	/**
	 * Troca o foco para a janela
	 */
	public void getFoco() {
		enable();
		getDriver().switchTo().frame(getWebElement());
	}

	/**
	 * Retorna o foco para a janela principal
	 */
	public void releaseFoco() {
		getDriver().switchTo().defaultContent();
	}

	public void registerPage(AbstractPage page) {
		page.getInitiater().addListener(this);
	}

	public Label getLabelTituloJanela() {
		return labelTituloJanela;
	}

	public void setLabelTituloJanela(Label labelTituloJanela) {
		this.labelTituloJanela = labelTituloJanela;
	}

	public Button getBotaoFechaJanela() {
		return botaoFechaJanela;
	}

	public void setBotaoFechaJanela(Button botaoFechaJanela) {
		this.botaoFechaJanela = botaoFechaJanela;
	}



	@Override
	public void windowClosed() {
		releaseFoco();
		
	}

	


}
