package automation.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.io.FileOutputStream; 
import java.io.IOException; 
//import api iText 

import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.reporters.EmailableReporter;
import org.testng.xml.XmlSuite;



import config.TestConfig;
import session.ThreadManager;

public class ArquivoUtils {
	private static String arquivoNovo;
	
	
	public static String tiraScreenshot() {
		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy'_'MM'_'dd' - 'HH'_'mm'_'ss'_'SSS", locale);
		String horario = formatador.format(calendar.getTime());
		String arquivo = horario + ".png";
		return tiraScreenshot(arquivo);
	}

	public static String tiraScreenshot(String nome) {
		ThreadManager.getSession().setNomeArquivoBug(nome);
		File screenshotSrcFile = ((TakesScreenshot) ThreadManager.getSession().getDriver()).getScreenshotAs(OutputType.FILE);
		File screenshotDestFile = new File(TestConfig.inputPath() + nome); 
		try {
			FileUtils.copyFile(screenshotSrcFile, screenshotDestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotDestFile.getPath();
	} 
	
	
	public static void deleteFiles(){
		String out = new File(TestConfig.outputPath()).toString();
		File file = new File(out);
		File afile[] = file.listFiles();
		for (File f : afile) {
    		f.delete();
    		
    	}
	}
	public static void deleteFilesInput(){
		String out = new File(TestConfig.inputPath()).toString();
		File file = new File(out);
		File afile[] = file.listFiles();
		for (File f : afile) {
    		f.delete();
    		
    	}
	}
	
	
	public static void deleteFile() {
		if (arquivoNovo != null) {
			File file = new File(arquivoNovo);
			file.delete();
		}
	}

	public static void createNewLog() {

		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy'_'MM'_'dd' - 'HH'_'mm'h'", locale);
		String horario = formatador.format(calendar.getTime());

		File oldFile = new File("log.html");
		File log_path = new File(TestConfig.resourceLogsPath() + "/log-" + horario + ".html");

		try {
			Files.copy(oldFile.toPath(), log_path.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteOldLog() {
		File file = new File("log.html");
		file.delete();
	}

	public static void createNewReport(ITestContext context) {

		IReporter report = new EmailableReporter();

		List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>();
		List<ISuite> iSuites = new ArrayList<ISuite>();
		xmlSuites.add(context.getSuite().getXmlSuite());
		iSuites.add(context.getSuite());

		report.generateReport(xmlSuites, iSuites, TestConfig.resourceReportsPath());

		Locale locale = new Locale("pt", "BR");
		GregorianCalendar calendar = new GregorianCalendar();
		SimpleDateFormat formatador = new SimpleDateFormat("yyyy'_'MM'_'dd' - 'HH'_'mm'h'", locale);
		String horario = formatador.format(calendar.getTime());

		File oldFile = new File(TestConfig.resourceReportsPath() + "/emailable-report.html");
		File report_path = new File(TestConfig.resourceReportsPath() + "/Relatorio - " + horario + ".html");

		try {
			Files.copy(oldFile.toPath(), report_path.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		oldFile.delete();

	}

	public static void createNewReport(ITestContext context, File report_path) {

		IReporter report = new EmailableReporter();

		List<XmlSuite> xmlSuites = new ArrayList<XmlSuite>();
		List<ISuite> iSuites = new ArrayList<ISuite>();
		xmlSuites.add(context.getSuite().getXmlSuite());
		iSuites.add(context.getSuite());

		report.generateReport(xmlSuites, iSuites, TestConfig.resourceReportsPath());



		File oldFile = new File(TestConfig.resourceReportsPath() + "/emailable-report.html");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		try {
			Files.copy(oldFile.toPath(), report_path.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		oldFile.delete();

	}

	public static void createFailedSuite(ITestContext context, File suite_path) {

		StringBuilder sbFailedMethods = new StringBuilder();
		sbFailedMethods.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sbFailedMethods.append("<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">");
		sbFailedMethods.append(
				"<suite thread-count=\"50\" guice-stage=\"DEVELOPMENT\" name=\"Failed suite\" parallel=\"methods\">");
		sbFailedMethods.append("<test name=\"Error(failed)\" parallel=\"methods\">");
		sbFailedMethods.append("<classes>");

		HashMap<String, HashMap<String, ITestNGMethod>> mapClass = new HashMap<String, HashMap<String, ITestNGMethod>>();

		List<ITestNGMethod> methods = new ArrayList<>();
		methods.addAll(context.getFailedTests().getAllMethods());


		for (ITestNGMethod method : methods) {
			if (!mapClass.containsKey(method.getTestClass().getName())) {
				mapClass.put(method.getTestClass().getName(), new HashMap<String, ITestNGMethod>());
			}

			mapClass.get(method.getTestClass().getName()).put(method.getMethodName(), method);

			for (String metDep : method.getMethodsDependedUpon()) {
				getDependsOnMethods(context, mapClass, method, metDep);
			}

		}

		for (String clas : mapClass.keySet()) {

	

			sbFailedMethods.append("<class name=\"" + clas + "\">");

			sbFailedMethods.append("<methods>");
			for (Entry<String, ITestNGMethod> entry : mapClass.get(clas).entrySet()) {

				ITestNGMethod method = entry.getValue();
				if (method != null) {
					

					sbFailedMethods.append("<include name=\"" + method.getMethodName() + "\" invocation-numbers=\""
							+ method.getFailedInvocationNumbers().toString().replace("[", "").replace(",", "")
									.replace("]", "")
							+ "\"/>");
					
				} else {
					String methodName = entry.getKey().substring(entry.getKey().lastIndexOf(".") + 1,
							entry.getKey().length());
					sbFailedMethods.append("<include name=\"" + methodName + "\"/>");
				}
			}

			sbFailedMethods.append("</methods>");
			sbFailedMethods.append("</class>");
			

		}

		sbFailedMethods.append("</classes>");
		sbFailedMethods.append("</test>");
		sbFailedMethods.append("</suite> ");


		FileWriter arq;
		try {
			arq = new FileWriter(suite_path);
			PrintWriter gravarArq = new PrintWriter(arq);

			gravarArq.printf(sbFailedMethods.toString());
			arq.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

//		
	}

	private static void getDependsOnMethods(ITestContext context,
			HashMap<String, HashMap<String, ITestNGMethod>> mapClass, ITestNGMethod method, String metDep) {

		if (metDep != null) {
			ITestNGMethod methodDependsOn = getMethodByName(context, metDep);

			mapClass.get(method.getTestClass().getName()).put(metDep, methodDependsOn);
			if (methodDependsOn != null && methodDependsOn.getMethodsDependedUpon() != null
					&& methodDependsOn.getMethodsDependedUpon().length > 0) {
				for (String metDepend : method.getMethodsDependedUpon()) {
					getDependsOnMethods(context, mapClass, methodDependsOn, metDepend);
				}
			}
		}
	}

	private static ITestNGMethod getMethodByName(ITestContext context, String methodName) {
		for (int i = 0; i < context.getAllTestMethods().length; i++) {
			if (methodName.endsWith(context.getAllTestMethods()[i].getMethodName())) {
				ITestNGMethod dependsOnMeth = context.getAllTestMethods()[i];
				return dependsOnMeth;
			}
		}
		return null;

	}

	public static InputStream projectResourceAsStream(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}

	public static File projectResourceAsFile(String name) {
		URL url = Thread.currentThread().getContextClassLoader().getResource(name);
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return file;
	}

}
