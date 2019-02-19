package session;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import automation.Pages;
import automation.utils.TestData;

public class Session {

	private final WebDriver driver;
	private final Map<String, String> errors;

	private Pages pages;
	private String issueTitle;
	private String nomeArquivoBug;
	private Logger log;
	private Integer nrPassos = 0;
	private SoftAssert softAssert;
	private TestData currentDTO;

	public Session(WebDriver driver) {
		this.driver = driver;
		this.errors = new HashMap<String, String>();
		this.pages = new Pages(driver);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setLog(Logger log) {
		this.log = log;
	}
	public Logger getLog() {
		return log;
	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String tituloBug) {
		this.issueTitle = tituloBug;
	}


	public String getNomeArquivoBug() {
		return nomeArquivoBug;
	}
	
	public void setNomeArquivoBug(String nomeArquivoBug) {
		this.nomeArquivoBug = nomeArquivoBug;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(String error, String exception) {
		errors.put(error, exception);
	}


	public Integer getNrPassos() {
		return nrPassos;
	}

	public void setNrPassos(Integer nrPassos) {
		this.nrPassos = nrPassos;
	}
	
	public void incrementaNrPassos() {
		nrPassos++;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages paginas) {
		this.pages = paginas;
	}
	
	public SoftAssert getSoftAssert() {
		return softAssert;
	}

	public void setSoftAssert(SoftAssert softAssert) {
		this.softAssert = softAssert;
	}

	public TestData getCurrentDTO() {
		return currentDTO;
	}

	public void setCurrentDTO(TestData currentDTO) {
		this.currentDTO = currentDTO;
	}
	
}
