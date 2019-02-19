package webcomponents;

import org.openqa.selenium.By;

import automation.utils.LogUtils;

public class GridPaginada extends Grid {

	private Label textoTitulo = new Label(By.xpath("//fieldset[@class='dataTable']/legend"), "", false);
	private TextBox campoNumeroPagina = new TextBox(By.xpath("//input[@class='ui-pg-input page-input']"), "", true);
	private Button botaoPaginaInicial = new Button(By.xpath("//a[span[@class='ui-icon ui-icon-seek-first']]"), "", true);
	private Button botaoPaginaAnterior = new Button(By.xpath("//a[span[@class='ui-icon ui-icon-seek-prev']]"), "", true);
	private Button botaoPaginaSeguinte = new Button(By.xpath("//a[span[@class='ui-icon ui-icon-seek-next']]"), "", true);
	private Button botaoPaginaFinal = new Button(By.xpath("//a[span[@class='ui-icon ui-icon-seek-end']]"), "", true);
	private Combo comboQuantidadeProcessos = new Combo(By.className("ui-pg-selbox"), "", true);

	public GridPaginada(By elementAddress, String propertyBind, boolean loadPanel) {
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
		campoNumeroPagina.onChange();	//TODO: ajustar para que funcione a troca de foco
		LogUtils.info("Navegando para a página " + numeroPagina + " de processos");
	}

	/**
	 * Encaminha a grid para a pagina inicial
	 */
	public void clicarBotaoPaginaInicial() {
		botaoPaginaInicial.click();
		LogUtils.info("Navegando para primeira página da lista de processos");
	}

	/**
	 * Encaminha a grid para a pagina anterior
	 */
	public void clicarBotaoPaginaAnterior() {
		botaoPaginaAnterior.click();
		LogUtils.info("Navegando para página anterior da lista de processos");
	}

	/**
	 * Encaminha a grid para a pagina seguinte
	 */
	public void clicarBotaoPaginaSeguinte() {
		botaoPaginaSeguinte.click();
		LogUtils.info("Navegando para próxima página da lista de processos");
	}

	/**
	 * Encaminha a grid para a pagina final
	 */
	public void clicarBotaoPaginaFinal() {
		botaoPaginaFinal.click();
		LogUtils.info("Navegando para última página da lista de processos");
	}

	/**
	 * Altera a quantidade de processos a serem mostrado na grid conforme o valor informado
	 * @param quantidade
	 */
	public void selecionarQuantidadeProcessos(String quantidade) {
		comboQuantidadeProcessos.select(quantidade);
		LogUtils.info("Reconfigurando quantidade de processos mostrados por página");
	}

	@Override
	public String montaElementAddress(String textoLinha) {
		return "id('" + getElementId() + "')//tr[td[span[contains(text(), '" + textoLinha + "')]]]";
	}

	@Override
	public String montaElementAddress(String textoLinha, String nomeColuna) {
		return "id('" + getElementId() + "')//tr[td[span[contains(text(), '" + textoLinha + "')]]]//td[" + getColunaIndex(nomeColuna) + "]";
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
