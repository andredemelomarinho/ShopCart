package paginas;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.internal.runners.statements.Fail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import CommonMethods.CommonMethods;
import session.ThreadManager;

public class PaginaCarrinhoCompras {
	
	CommonMethods cm = new CommonMethods();
	public  PaginaCarrinhoCompras(WebDriver driver) {
    	this.getDriver();
	}
	public WebDriver getDriver() {
		return ThreadManager.getSession().getDriver();
	}
	public void clicaCategoria(){
		getDriver().findElement(By.id("open-categories-btn")).click();
	}
	public void selecionaCategoria(String categoria){
		clicaCategoria();
		getDriver().findElement(By.xpath("//li[text()='"+categoria+"']")).click();
	}
	public void clicaTodosProduto(){
		List<WebElement> prod =getDriver().findElements(By.xpath("//li/div[2]"));
		if(prod.size()>0){
		for(WebElement ele:prod){
			ele.click();
		}
		}
	}
	public void clicaBotaoCarrinho(){
		getDriver().findElement(By.id("cart-btn")).click();
	}
	public void adicionarProduto(int qtd,String produto){
		if(produto.contentEquals("Brigadeiro")){
			produto ="add-product-4-qtd";
		}else if(produto.contentEquals("Rissole médio")){
			produto ="add-product-3-qtd";
		}
			
		for(int i=0;i<qtd; i++){
		getDriver().findElement(By.id(produto)).click();
		}
	}
	public void diminuirProduto(String produto,int qtd){
		if(produto.contentEquals("Brigadeiro")){
			produto ="remove-product-4-qtd";
		}else if(produto.contentEquals("Rissole médio")){
			produto ="remove-product-3-qtd";
		}
			
		for(int i=0;i<qtd; i++){
		getDriver().findElement(By.id(produto)).click();
		}
	}
	public void finalizarCompra(){
		getDriver().findElement(By.id("finish-checkout-button")).click();
	}
	
	public void validaValorTotal() throws ParseException{
		double valor=0;
		double valorItem=0;
		double valorTotal=0;
		int i=0;
		List<WebElement> itens =getDriver().findElements(By.xpath("//li/p[contains(@id,'product')]"));
		for(WebElement item : itens){
		int qtd =Integer.parseInt(getDriver().findElement(By.id("product-"+i+"-qtd")).getText());
		valorItem= cm.converte(item.getText().replace("R$ ", ""))*qtd;
		valor += cm.converte(item.getText().replace("R$ ", ""))*qtd;
		 System.out.println(valorItem);
		 i++;
		}
		valorTotal= cm.converte(pegaValorTotal().replace("R$ ", ""));
		Assert.assertEquals(valorTotal, valor, 0);
	}
	public String pegaValorTotal(){
		String valor =getDriver().findElement(By.id("price-total-checkout")).getText();
		return valor;
		
	}
	public void fechar(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDriver().findElement(By.xpath("//button[text()='Fechar']")).click();
	}
	public void validaConfirmaCompra(){
		WebElement ele =getDriver().findElement(By.xpath("//h2[text()='Pedido realizado com sucesso!']"));
		if(ele.isDisplayed()){
			System.out.println("Compra efetuada");
		}
		else{
			Assert.fail("Não foi encontrado a mensagem");
		}
	}
	public void adicionarProdutos(String produto){
		getDriver().findElement(By.id("add-product-3-btn")).click();;
	}
}
