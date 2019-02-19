package cucumberTest;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.ITestContext;
import com.itextpdf.text.DocumentException;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import session.ThreadManager;
import automation.utils.*;



@RunWith(Cucumber.class)
@CucumberOptions(
	strict = true,
	features = { "src/test/resources/features", },
	monochrome = false,
	glue = { "stepDefinition" },
	plugin = { "pretty", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"},
	tags = { "@comprasDoces,@comprarBebidas" } 
)

public class TestRunner {

	static JpgToPdf pdf = new JpgToPdf();
	static ITestContext testContext=null;
	@AfterClass
	public static void closeDriver() throws   InstantiationException, IllegalAccessException, MalformedURLException, DocumentException, IOException {
		pdf.criarJpgToPdfTeste();
		ThreadManager.getSession().getDriver().close();
		ThreadManager.getSession().getDriver().quit();
								
	}
	
	@BeforeClass
	public static void testStarts() throws   InstantiationException, IllegalAccessException {
		ArquivoUtils.deleteFilesInput();						
	}

}
