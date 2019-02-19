package stepDefinition;

import automation.Pages;
import automation.utils.ArquivoUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import paginas.PaginaCarrinhoCompras;
import session.ThreadManager;

public class CarrinhoComprasStepDefinitions {

	private Pages getPages() {
		return ThreadManager.getSession().getPages();
	}

	@When("^Adicionar todos os produtos ao carrinho$")
	public void adicionar_todos_os_produtos_ao_carrinho() throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).clicaTodosProduto();
		ArquivoUtils.tiraScreenshot("COMPRAS1.jpg");
	}

	@When("^Selecionar categoria \"([^\"]*)\"$")
	public void selecionar_categoria(String categoria) throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).selecionaCategoria(categoria);
		ArquivoUtils.tiraScreenshot("COMPRAS2.jpg");
	}

	@When("^Acessar o carrinho$")
	public void acessar_o_carrinho() throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).clicaBotaoCarrinho();
		ArquivoUtils.tiraScreenshot("COMPRAS3.jpg");
	}

	@When("^Aumentar a quantidade do produto \"([^\"]*)\" em (\\d+) unidades$")
	public void aumentar_a_quantidade_do_produto_em_unidades(String produto, int qtd) throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).adicionarProduto(qtd,produto);
		ArquivoUtils.tiraScreenshot("COMPRAS4.jpg");
	}

	@When("^Clicar no botão Finalizar Compra$")
	public void clicar_no_botão_Finalizar_Compra() throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).finalizarCompra();
		ArquivoUtils.tiraScreenshot("COMPRAS5.jpg");
	}

	@Then("^Validar a mensagem \"([^\"]*)\"$")
	public void validar_a_mensagem(String arg1) throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).validaConfirmaCompra();
		ArquivoUtils.tiraScreenshot("COMPRAS6.jpg");
	}

	@Then("^Clicar no botão Fechar$")
	public void clicar_no_botão_Fechar() throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).fechar();
		ArquivoUtils.tiraScreenshot("COMPRAS7.jpg");
	}

	@When("^Adicionar o produto \"([^\"]*)\" ao carrinho$")
	public void adicionar_o_produto_ao_carrinho(String produto) throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).adicionarProdutos(produto);
		ArquivoUtils.tiraScreenshot("COMPRAS8.jpg");
	}

	@When("^Diminuir a quantidade do produto \"([^\"]*)\" em (\\d+) unidades$")
	public void diminuir_a_quantidade_do_produto_em_unidades(String produto, int qtd) throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).diminuirProduto(produto,qtd);
		ArquivoUtils.tiraScreenshot("COMPRAS9.jpg");
	}

	@When("^Validar o valor total dos produtos$")
	public void validar_o_valor_total_dos_produtos() throws Throwable {
		getPages().get(PaginaCarrinhoCompras.class).validaValorTotal();
		ArquivoUtils.tiraScreenshot("COMPRAS10.jpg");
	}


	
}
