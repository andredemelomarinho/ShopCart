package automation;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import automation.utils.*;
import config.TestConfig;
import drive.DriverFactory;
import session.ThreadManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import session.Session;
import session.SuiteConfig;

public class AbstractTest {

	protected String baseUrl;

	protected AbstractTest getInternalTest() {
		return this;
	}

	public void runTest(String method, java.lang.Class<?> testClass) {
		
	}

	@BeforeTest
	public void setUpTest(){
//		ExtentManager.getReporter(REPORTS_PATH +SEPARATOR+ getTestName()+ SEPARATOR+PropertiesUtils.getTestProperty("reportfile"));
	}

	@BeforeSuite
	public void setUpSuite() throws MalformedURLException {
		ThreadManager.getSuiteSession().setAmbiente(PropertiesUtils.environment);

		Locale locale = new Locale("pt","BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy'_'MM'_'dd' - 'HH'_'mm'_'ss'_'SS",locale);
		String horario = formatador.format(calendar.getTime());
		SuiteConfig.getSuiteSession().setTestName(horario.toString());
	}

	@BeforeClass
	public void setUpClass(String sys) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
			baseUrl = PropertiesUtils.getAppProperty("URL");
		
	}

	@BeforeMethod
	public void setUpMethod(ITestContext testContext, Method method, Object[] data) throws Exception {
		Session context = ThreadManager.getSession();
		if(context == null) {
			DriverFactory factory = new DriverFactory();
	        try {
				WebDriver driver = factory.getDriver(false);
				ThreadManager.setSession(new Session(driver));
				ThreadManager.getSession().setSoftAssert(new SoftAssert());
				getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

				String methodName = (method == null) ? "" : method.getName() + " ";
				ThreadManager.getSession().setLog(Logger.getLogger(methodName + Thread.currentThread().getId()));
				PropertyConfigurator.configure("src/test/resources/properties/log4j.properties");
	        } catch(Exception e) {
	        	e.printStackTrace();
	        }
		}
	}

	public void setUpMethod() throws Exception {
		setUpMethod(null, null, null);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		String nomeArquivo =ThreadManager.getSession().getIssueTitle();	
	 	Date dataHoraAtual = new Date();
    	String data = new SimpleDateFormat("dd_MM_yyyy").format(dataHoraAtual);
    	String hora = new SimpleDateFormat("HHmmss").format(dataHoraAtual);
		
    	String outputFile =nomeArquivo+data+hora+".pdf";
    	File root = new File(TestConfig.inputPath());
        String out = new File(TestConfig.outputPathEvidencia()).toString();
        List<String> files = new ArrayList<String>();
        File file = new File(root.toString());
        File afile[] = file.listFiles();
        
    	for (File f : afile) {
    		files.add(f.getName());
    		System.out.println(f);
    		
    	}
    	
        
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(out, outputFile)));
        document.open();
        for (String f : files) {
            document.newPage();
            Image image = Image.getInstance(new File(root, f).getAbsolutePath());
            image.setAbsolutePosition(0, 0);
            image.setBorderWidth(0);
            image.scaleAbsolute(PageSize.A4);
            document.add(image);
        }
        document.close();
        file.delete();
    }
		
	
	
	@AfterClass
	public final void tearDownClass() {
		getDriver().close();
	}

	@AfterTest
	public final void tearDownTest(ITestContext testContext) throws Exception {
		getDriver().quit();
		getDriver().close();
		
	}

	@AfterSuite
	public final void tearDownSuite(ITestContext testContext) throws Exception {
		getDriver().quit();
		getDriver().close();
	}

	/**
	 * Fecha todas as janelas/modais que nao sejam a janela principal. Em seguida alterna o controle para a janela principal.
	 * @param mainWindow
	 */
	protected void closeSecondaryWindows(String mainWindow) {
		Set<String> janelasSecundarias = getDriver().getWindowHandles(); // get all the window handles after the popup window appears
		janelasSecundarias.remove(mainWindow); // remove all the handles from before the popup window appears

		for(Object janelaAtual: janelasSecundarias){
			getDriver().switchTo().window((String)janelaAtual);
			getDriver().close(); //Close the new window
		}
		getDriver().switchTo().window(mainWindow); // reopen main window
	}

	protected WebDriver getDriver() { 
		return ThreadManager.getSession().getDriver();
	}

	protected Pages getPaginas() {
		return ThreadManager.getSession().getPages();
	}

}
