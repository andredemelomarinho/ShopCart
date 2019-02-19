package webcomponents;

import org.openqa.selenium.By;

import webcomponents.Button;
import webcomponents.Combo;
import webcomponents.Grid;
import webcomponents.Label;
import webcomponents.TextBox;

public class GridPlc extends Grid {

	private Label textoTitulo = new Label(By.className("ui-jqgrid-title"), "", false);
	private TextBox campoNumeroPagina = new TextBox(By.className("ui-pg-input"), "", true);
	private Button botaoPaginaInicial = new Button(By.id("first_plc-grid-navegador"), "", true);
	private Button botaoPaginaAnterior = new Button(By.id("prev_plc-grid-navegador"), "", true);
	private Button botaoPaginaSeguinte = new Button(By.id("next_plc-grid-navegador"), "", true);
	private Button botaoPaginaFinal = new Button(By.id("last_plc-grid-navegador"), "", true);
	private Combo comboQuantidadeProcessos = new Combo(By.className("ui-pg-selbox"), "", true);

	public GridPlc(By elementAddress, String propertyBind, boolean loadPanel) {
		super(elementAddress, propertyBind, loadPanel);
	}

	/**
	 * Retorna titulo da grid
	 * @return
	 */
	public String getTitulo() {
		return textoTitulo.getText();
	}

	/**
	 * Muda a pagina da grid conforme o numero informado
	 * @param numeroPagina
	 */
	public void mudarPaginaProcessos(String numeroPagina) {
		campoNumeroPagina.type(numeroPagina);
		campoNumeroPagina.pressEnter();
	}

	/**
	 * Encaminha a grid para a pagina inicial
	 */
	public void clicarBotaoPaginaInicial() {
		botaoPaginaInicial.click();
	}

	/**
	 * Encaminha a grid para a pagina anterior
	 */
	public void clicarBotaoPaginaAnterior() {
		botaoPaginaAnterior.click();
	}

	/**
	 * Encaminha a grid para a pagina seguinte
	 */
	public void clicarBotaoPaginaSeguinte() {
		botaoPaginaSeguinte.click();
	}

	/**
	 * Encaminha a grid para a pagina final
	 */
	public void clicarBotaoPaginaFinal() {
		botaoPaginaFinal.click();
	}

	/**
	 * Altera a quantidade de processos a serem mostrado na grid conforme o valor informado
	 * @param quantidade
	 */
	public void selecionarQuantidadeProcessos(String quantidade) {
		comboQuantidadeProcessos.select(quantidade);
	}

	public Label getTextoTitulo() {
		return textoTitulo;
	}

	public void setTextoTitulo(Label textoTitulo) {
		this.textoTitulo = textoTitulo;
	}

	public TextBox getCampoNumeroPagina() {
		return campoNumeroPagina;
	}

	public void setCampoNumeroPagina(TextBox campoNumeroPagina) {
		this.campoNumeroPagina = campoNumeroPagina;
	}

	public Button getBotaoPaginaInicial() {
		return botaoPaginaInicial;
	}

	public void setBotaoPaginaInicial(Button botaoPaginaInicial) {
		this.botaoPaginaInicial = botaoPaginaInicial;
	}

	public Button getBotaoPaginaAnterior() {
		return botaoPaginaAnterior;
	}

	public void setBotaoPaginaAnterior(Button botaoPaginaAnterior) {
		this.botaoPaginaAnterior = botaoPaginaAnterior;
	}

	public Button getBotaoPaginaSeguinte() {
		return botaoPaginaSeguinte;
	}

	public void setBotaoPaginaSeguinte(Button botaoPaginaSeguinte) {
		this.botaoPaginaSeguinte = botaoPaginaSeguinte;
	}

	public Button getBotaoPaginaFinal() {
		return botaoPaginaFinal;
	}

	public void setBotaoPaginaFinal(Button botaoPaginaFinal) {
		this.botaoPaginaFinal = botaoPaginaFinal;
	}

	public Combo getComboQuantidadeProcessos() {
		return comboQuantidadeProcessos;
	}

	public void setComboQuantidadeProcessos(Combo comboQuantidadeProcessos) {
		this.comboQuantidadeProcessos = comboQuantidadeProcessos;
	}


}
