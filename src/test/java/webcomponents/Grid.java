package webcomponents;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webcomponents.Button;
import webcomponents.Check;
import webcomponents.Combo;
import webcomponents.Component;
import webcomponents.Label;
import webcomponents.TextBox;

public class Grid extends Component {

	private Label quantidadeItens = new Label(By.xpath("//td[@id='plc-grid-navegador_right']//div"), "", false);
	
	public Grid(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	public Label getLabelQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(Label quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	/**
	 * Retorna o botao desejado
	 * @param linha
	 * @param elemento
	 * @return
	 */
	public Button getBotao(String linha, String elemento) {
		return new Button(By.xpath(linha + elemento), "", true);
	}

	/**
	 * Retorna o botao desejado
	 * @param textoLinha
	 * @param nomeColuna
	 * @param toolTip
	 * @return
	 */
	public Button getBotao(String textoLinha, String nomeColuna, String toolTip) {
		By elementAddress = By.xpath(montaElementAddress(textoLinha, nomeColuna) + "//span[@title='" + toolTip + "']");
		return new Button(elementAddress, "", true);
	}

	/**
	 * Retorna o botaoCommandLink
	 * @param textoLinha
	 * @param nomeColuna
	 * @param toolTip
	 * @return
	 */
	public Button getBotaoCommandLink(String textoLinha, String nomeColuna, String toolTip) {
		By elementAddress = By.xpath(montaElementAddress(textoLinha, nomeColuna) + "//img[@title='" + toolTip + "']");
		return new Button(elementAddress, "", true);
	}

	/**
	 * Retorna o combo desejado
	 * @param linha
	 * @param elemento
	 * @return
	 */
	public Combo getCombo(String linha, String elemento) {
		return new Combo(By.xpath(linha + elemento), "", true);
	}

	/**
	 * Retorna o combo desejado
	 * @param textoLinha
	 * @param nomeColuna
	 * @param nomeClass TODO
	 * @return
	 */
	public Combo getCombo(String textoLinha, String nomeColuna, String nomeClass) {
		By elementAddress = By.xpath(montaElementAddress(textoLinha, nomeColuna) + "//select[@class='" + nomeClass + "']");
		return new Combo(elementAddress, "", true);
	}

	/**
	 * Retorna o checkBox desejado
	 * @param textoLinha
	 * @param nomeColuna
	 * @return
	 */
	public Check getCheck(String textoLinha) {
		By elementAddress = By.xpath(montaElementAddress(textoLinha) + "//input[@type='checkbox']");

		return new Check(elementAddress, "", true);
	}

	public Check getCheckAlternativo(String idDocumento) {
		By elementAddress = By.xpath("id('corpo:formulario:tableDocumentos:tableDocumento')//tr[td[a[@id='assinarDocumento' and contains(@onclick, '" + idDocumento + "')]]]//input[@type='checkbox']");

		return new Check(elementAddress, "", true);
	}

	/**
	 * Retorna o checkBox desejado
	 * @param textoLinha
	 * @param nomeColuna
	 * @return
	 */
	public Check getCheck(String textoLinha, String nomeColuna) {
		By elementAddress = By.xpath(montaElementAddress(textoLinha, nomeColuna) + "//input[@type='checkbox']");

		return new Check(elementAddress, "", true);
	}

	/**
	 * Retorna o textBox desejado
	 * @param textoLinha
	 * @return
	 */
	public TextBox getTextBox(String textoLinha) {
		By elementAddress = By.xpath(montaElementAddress(textoLinha) + "//input[@type='text']");

		return new TextBox(elementAddress, "", true);
	}

	/**
	 * Retorna o texto desejado
	 * @param textoLinha
	 * @param nomeColuna
	 * @return
	 */
	public Label getLabel(String textoLinha, String nomeColuna) {
		return new Label(getElementAddress(textoLinha, nomeColuna), "", false);
	}

	/**
	 * Monetar um xpath padrao
	 * @param textoLinha
	 * @return
	 */
	public String montaElementAddress(String textoLinha) {
		return "";
	}

	/**
	 * Monta um xpath padrao
	 * @param textoLinha
	 * @param nomeColuna
	 * @return
	 */
	public String montaElementAddress(String textoLinha, String nomeColuna) {
		return "";
	}

	/**
	 * Retorna o endereco do elemento
	 * @param textoLinha
	 * @param nomeColuna
	 * @return
	 */
	private By getElementAddress(String textoLinha, String nomeColuna) {
		return By.xpath(montaElementAddress(textoLinha, nomeColuna));
	}

	/**
	 * Retorna a lista de colunas da grid
	 * @return
	 */
	public ArrayList<String> getNomeColunas() {
		List<WebElement> listaWebElement = getElements(By.xpath("id('" + getElementId() + "')//th"));
		ArrayList<String> listaColunas = new ArrayList<String>();
		int i = 0;

		for(WebElement webElement : listaWebElement) {
			String nomeColuna = getElements(By.xpath("id('" + getElementId() + "')//th")).get(i).getText();

			listaColunas.add(nomeColuna);
			i++;
		}

		return listaColunas;
	}

	/**
	 * Metodo retorna lista de texto de uma coluna
	 * @param nomeColuna
	 * @return
	 */
	public ArrayList<Label> getTextosColuna(String nomeColuna) {
		List<WebElement> listaWebElement = getElements(By.xpath("id('" + getElementId() + "')//tr/td[" + getColunaIndex(nomeColuna)  + "]"));
		ArrayList<Label> listaTexto = new ArrayList<Label>();

		for(WebElement webElement : listaWebElement) {
			Label textoColuna = new Label(null, null, false);

			textoColuna.webElement = webElement;
			listaTexto.add(textoColuna);
		}

		return listaTexto;
	}

	/**
	 * Retorna o indice atual da coluna. Deve ser somado mais 1 ao resultado pois na lista vinda do xpath comeca por 1, nao 0 a contagem de itens
	 * @param nomeColuna
	 * @return
	 */
	protected long getColunaIndex(String nomeColuna) {
		ArrayList<String> listaColunas = getNomeColunas();

		for(int index = 0; index < listaColunas.size(); index++) {
			if(nomeColuna.equals(listaColunas.get(index))) {
				return index + 1;
			}
		}

		return -1;
	}

	/**
	 * Retorna os elementos de um endereco
	 * @param elementAddress
	 * @return
	 */
	protected List<WebElement> getElements(By elementAddress) {
		return getDriver().findElements(elementAddress);
	}

}
