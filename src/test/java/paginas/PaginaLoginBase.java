package paginas;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import org.testng.Assert;

import session.ThreadManager;

import automation.utils.PropertiesUtils;
import automation.AbstractPage;

import webcomponents.Button;
import webcomponents.TextBox;

public class PaginaLoginBase extends AbstractPage{
	
	public PaginaLoginBase(WebDriver driver) {
	}



	/**
	 * Recebe URL como parametro para abrir pagina
	 * @param url
	 */
	//TODO: colocar como privado e ir criando os métodos para abrir páginas especificas
	public void open(String url) {
		if (ThreadManager.getSession() == null){
			System.out.println("++++++++++++++++++++++++++++++++Sessão NULA+++++++++++++++++++++++++++++++++++++++++");
		}
		if (ThreadManager.getSession().getDriver() == null){
			System.out.println("++++++++++++++++++++++++++++++++Driver NULO+++++++++++++++++++++++++++++++++++++++++");
		}
		try{
		ThreadManager.getSession().getDriver().get(url);//navigate().to(url);
		 ThreadManager.getSession().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}catch(TimeoutException toe){
		ThreadManager.getSession().getDriver().navigate().to(url);
		 ThreadManager.getSession().getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}catch(Exception ex){
//			LogUtils.error(ex, "Open URL");
		}
	}

	/**
	 * Abre pagina inicial do ethemis1g
	 * @param baseUrlst
	 */

	public void openLogin(String baseUrl) {
		System.out.print(baseUrl);
		
    	open(baseUrl);
		
	}

	
	

	
	
}
