package automation.utils;

import session.ThreadManager;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.MediaEntityBuilder;

import automation.utils.PropertiesUtils;
import config.TestConfig;

public class LogUtils {
	private static final boolean logAction = PropertiesUtils.getTestProperty("log.action") != null ? Boolean.parseBoolean(PropertiesUtils.getTestProperty("log.action")): false;
//	private static final boolean logStepScreenShot = PropertiesUtils.getTestProperty("log.stepScreenShot") != null ? Boolean.parseBoolean(PropertiesUtils.getTestProperty("log.stepScreenShot")): false;

	private static ExtentReports extent;
//	private static ExtentHtmlReporter htmlReporter;
	private static ExtentTest extentTest  ;
    public static void error(Throwable e, String teste){
    	ThreadManager.getSession().incrementaNrPassos(); 
//		String erroLog = "ERRO na validação: "+teste;
		String erro = "ERRO na validação: "+teste+" - "+e.toString()+"</warning>";
		ThreadManager.getSession().setErrors(teste, erro);
//		ThreadManager.getSession().getLog().error(erroLog);
		

    }

	public void logPrint(String strLog) throws IOException, ClassNotFoundException{
		ArquivoUtils.tiraScreenshot(strLog);
    	extentTest = extent.createTest(new GherkinKeyword("Feature"), "Refund item");
    	extentTest.log(Status.INFO, strLog, MediaEntityBuilder.createScreenCaptureFromPath(TestConfig.outputPath()).build());
    }
 
    public static void error(Throwable e, String teste, String imagePath){
    	ThreadManager.getSession().incrementaNrPassos();
//		String erroLog = "ERRO na validação: "+teste;
		String erro = "ERRO na validação: "+teste+" - "+e.toString()+"</warning>";
		ThreadManager.getSession().setErrors(teste, erro);
//		ThreadManager.getSession().getLog().error(erroLog);
//		String img = ThreadManager.getSession().getExtentTest().addScreenCapture(imagePath);
//		ThreadManager.getSession().getExtentTest().log(LogStatus.ERROR, teste+": "+ img);
		
    }
    
    public static void info(Throwable e, String teste, String imagePath){
    	ThreadManager.getSession().incrementaNrPassos();
//		String erroLog = "ERRO na validação: "+teste;
		String erro = "ERRO na validação: "+teste+" - "+e.toString()+"</warning>";
		ThreadManager.getSession().setErrors(teste, erro);
//		ThreadManager.getSession().getLog().info(erroLog);
//		String img = ThreadManager.getSession().getExtentTest().addScreenCapture(imagePath);
//		ThreadManager.getSession().getExtentTest().log(LogStatus.INFO, teste+": "+ img);
		
    }
    
    public static void info(String teste){
    	ThreadManager.getSession().incrementaNrPassos();
//    	ThreadManager.getSession().getLog().info(teste);
//    	ThreadManager.getSession().getExtentTest().log(LogStatus.INFO, teste);
    }
    
    public static void action(String action){
    	if (logAction){
//    		String value = "[*]	"+action; 
	    	ThreadManager.getSession().incrementaNrPassos();
//	    	ThreadManager.getSession().getLog().info(value);
//	    	if (logStepScreenShot){
//	    		String img = ThreadManager.getSession().getExtentTest().addScreenCapture(ArquivoUtils.tiraScreenShot());
//	    		ThreadManager.getSession().getExtentTest().log(LogStatus.UNKNOWN, value+": "+ img);
//	    	}else{
//	    		ThreadManager.getSession().getExtentTest().log(LogStatus.UNKNOWN, value);
//	    	}
    	}
    }

}
