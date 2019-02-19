package test;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import automation.AbstractPage;
import automation.utils.ArquivoUtils;
import dto.SimuladorDTO;
import session.ThreadManager;

public class Simulacao extends AbstractPage {
	private WebDriver driver;
	
	public Simulacao(WebDriver driver){
		this.driver = driver;
	}
	
	public void open(String url) {
		driver.navigate().to(url);
	}
	public void simular(){
		
	}
}
