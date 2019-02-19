package automation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;





public class SeleniumUtils {

	public static void checkUncheckCheckbox(Boolean check, WebElement checkbox) {
		if (check.equals(true)) {
			if(!checkbox.isSelected()){
				checkbox.click();
				LogUtils.action("	- Check at :" + checkbox.toString());
			}
		} else if (check.equals(false)) {
			if(checkbox.isSelected()){
				checkbox.click();
				LogUtils.action("	- Uncheck at :" + checkbox.toString());
			}
		}
	}

	public static void checkRadio(By by, WebDriver driver) {
		WebElement radio = driver.findElement(by);

		if(!radio.isSelected()){
			radio.click();
			checkLoaderPanel(driver);
			LogUtils.action("	- Check at :" + by.toString());
		}
	}

	public static void verificarElemento(By by, WebDriver driver) {
		driver.findElement(by);
	}

	public static void clicarElemento(By by, WebDriver driver) {
		driver.findElement(by).click();
		checkLoaderPanel(driver);
		LogUtils.action("	- Click at:" + by.toString());
	}

	public static void preencherCampo(By by, String value, WebDriver driver) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
		LogUtils.action("	- Set value ["+value+"] at :" + by.toString());
//		checkLoaderPanel(driver);
	}

	public static void preencheJavaScript(By elementAddress, String value, WebDriver driver) {
		String val ="";
		String elementId = elementAddress.toString().substring(elementAddress.toString().indexOf(':')+2);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		if(value != null){
			val = value.toString();
		}

		js.executeScript("document.getElementById('" + elementId + "').value = '" + val + "'");
		js.executeScript("document.getElementById('" + elementId + "').onchange();");
		
		LogUtils.info("	- Set value [" + value + "] at :" + elementAddress.toString());
	}

	public static void selecionarCombo(By by, String value, WebDriver driver) {
		new Select(driver.findElement(by)).selectByVisibleText(value);
		checkLoaderPanel(driver);
		LogUtils.action("	- Select value ["+value+"] at :" + by.toString());
	}

	public static void pesquisarMudarFoco(By by, WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('" + by + "').onchange();");
		checkLoaderPanel(driver);
	}
	public static void checkLoaderPanel(WebDriver driver) {
		for(int i = 0; i<10; i++){
			try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
				while(driver.findElement(By.id("loader-panel")).isDisplayed()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				return;
			} catch (Exception e) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				//System.out.println("Loader-panel não encontrado.");
				continue;
			}
		}
		/*		while(true){
			if(ThreadManager.getDriver().findElement(By.tagName("html")).isDisplayed()){
				try {
					ThreadManager.getDriver().findElement(by);
				} catch (Exception e) {
					System.out.println("Loader-panel não encontrado.");
					return;
				}
				if(!ThreadManager.getDriver().findElement(by).isDisplayed()){
					return;
				}
			}
		}*/
	}
	
	public static void fecharJanela(String title, WebDriver driver){
	    List<String> driverWindows = new ArrayList<String>();
	    driverWindows.addAll(driver.getWindowHandles());
	    
	    for (String win : driverWindows){
	    	driver.switchTo().window(win);
	    	if(driver.getTitle().equals(title)){
	    		driver.close();
	    	}
	    }
	}
	
	public static void trocarFocoJanela(String title, WebDriver driver){
	    List<String> driverWindows = new ArrayList<String>();
	    driverWindows.addAll(driver.getWindowHandles());
	    
	    for (String win : driverWindows){
	    	driver.switchTo().window(win);
	    	if(driver.getTitle().equals(title)){
	    		break;
	    	}
	    }
	}
	
	public static void esperaElementoVisivel(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));		
	}
	
	public static void esperaElementoClicavel(By by, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(by));		
	}
	
	public static List<WebElement> buscaElementosDentroDeUmElemento(WebElement element, By by){
		List<WebElement> elements = new ArrayList<WebElement>(); 
		elements = element.findElements(by);
//		for(WebElement elemento : elements){
//			System.out.println(elemento.getText());
//		}
		return elements;
	}
	
	public static int getNumeroPaginasDoDriver(int numeroEstimado, WebDriver driver){
		for(int i = 0; i<10; i++){
			if(driver.getWindowHandles().size()!=numeroEstimado){
				break;
			}
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return driver.getWindowHandles().size();
	}

}
