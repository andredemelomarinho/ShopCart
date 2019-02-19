package CommonMethods;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import automation.utils.LogUtils;
import config.TestConfig;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import session.ThreadManager;
import java.util.List;

public class CommonMethods {
	protected InputStream templateJasper;
	protected JasperPrint relatorio;
	protected Map<String,Object> parametros = new HashMap<String,Object>();
	public WebDriver getDriver() {
		return ThreadManager.getSession().getDriver();
	}

	public void highlightElement(WebElement element) {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: blue; border: 5px solid yellow;");

	}

	public String getNumeroProcessoFormatado(String processo) {
		String numeroProcesso = processo;
		numeroProcesso = numeroProcesso.replace(".", "");
		numeroProcesso = numeroProcesso.replace("-", "");
		return numeroProcesso;
	}

	public String getNumeroProtocoloFormatado(String protocolo) {
		protocolo = protocolo.substring(5);
		String numeroProtocolo = protocolo;
		numeroProtocolo = numeroProtocolo.replace(".", "");
		numeroProtocolo = numeroProtocolo.replace("-", "");
		return numeroProtocolo;
	}

	public double converte(String arg) throws java.text.ParseException {
		// obtem um NumberFormat para o Locale default (BR)
		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("pt", "BR"));
		// converte um número com vírgulas ex: 2,56 para double
		double number = nf.parse(arg).doubleValue();

		return number;
	}

	public BigDecimal valorBigDecimal(String numero) throws java.text.ParseException {

		BigDecimal bg = new BigDecimal(converte(numero)).setScale(2, RoundingMode.HALF_EVEN);
		return bg;
	}

	public void afterCenario() {
		getDriver().close();
	}

	public void copiarArquivos() throws IOException {

		File src = new File("C:\\tmp");
		String dstPath = "C:\\java\\";
		File dst;

		File[] files = src.listFiles();

		for (File f : files) {
			String fileName = f.getName();

			if (fileName.contains("File")) {

				dst = new File(dstPath + fileName);
				InputStream in = new FileInputStream(f);
				OutputStream out = new FileOutputStream(dst);
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
				f.delete();
			}
		}
	}

	public WebElement waitComponent(By address, int time) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), time);

			element = wait.until(ExpectedConditions.visibilityOfElementLocated(address));
			if (element == null) {
				LogUtils.info(
						" ---- failed attempt to wait for the component to be visible: [" + address.toString() + "]");
			}
		} catch (Exception e) {
			LogUtils.info(" ---- failed attempt to wait for the component to be visible: [" + address.toString() + "]");
		}
		return element;
	}

	public boolean setField(Object targetObject, String fieldName, Object fieldValue) {
		Field field;
		try {
			field = targetObject.getClass().getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field = null;
		}
		Class superClass = targetObject.getClass().getSuperclass();
		while (field == null && superClass != null) {
			try {
				field = superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				superClass = superClass.getSuperclass();
			}
		}
		if (field == null) {
			return false;
		}
		field.setAccessible(true);
		try {
			field.set(targetObject, fieldValue);
			return true;
		} catch (IllegalAccessException e) {
			return false;
		}
	}

	public void rollToElement(WebElement elemento) {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView()", elemento);
	}

	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void JpgToPdf(String nomeArquivo ) throws DocumentException, MalformedURLException, IOException {
		List<String> files = new ArrayList<String>();
	        File root = new File(TestConfig.inputPath());
	        File out = new File(TestConfig.outputPath());
	        String outputFile = nomeArquivo;
	        
	        File file = new File(root.toString());
	    	File afile[] = file.listFiles();
	    	for (File f : afile) {
	    		files.add(file.getName());
	    	}
	    	
	        //List<String> files = new ArrayList<String>();
	        //files.add("page1.jpg");
	        //files.add("page2.jpg");
	        
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
	    
	}
	
	
	public void geradorPDF(String nomeArquivo) throws MalformedURLException, IOException, DocumentException{
		 File root = new File(TestConfig.inputPath());
		 File out = new File(TestConfig.outputPath());
		 String outputFile = nomeArquivo;
		 Document document = new Document(); 
		 PdfWriter.getInstance(document, new FileOutputStream(new File(out, outputFile)));
		 document.open(); 
	        File file = new File(root.toString());
	    	File afile[] = file.listFiles();
	    	if (afile.length>0){
	    	for (File f : afile) {
	    		String arq =f.getName();
	    		System.out.println(f.getName());
	    		//document.newPage();
	    		//Image image = Image.getInstance(new File(root, arq).getAbsolutePath());
	    		
	    		document.add(new Paragraph("Testar a criação do paf sem imagem"));
	    		//document.add(image);
	    		
	            
	            //f.delete();
	    	}
	            
	    	}
	    
		
	}
	
	
	
	public void geraPDF(String arquivo) {
		geraPDF(new File(arquivo));
	}

		public JasperPrint geraRelatorio(){
			try{
				if(relatorio == null){
					//JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(boletos);
					relatorio = JasperFillManager.fillReport(templateJasper,parametros);
				}
				return relatorio;
			}catch(Exception e){
				
			}
			return relatorio;
		}
		public void geraPDF(File arquivo) {
			OutputStream out = null;
			
			try {
				out = new FileOutputStream(arquivo);
				geraPDF(out);
				
			} catch (FileNotFoundException e) {
				
			
			}finally {
				if(out != null) {
					try {
						out.close();
					} catch (IOException e) {
						
					}
				}
			}
		}
		public void geraPDF(OutputStream out){
			try {
				JasperPrint relatorio = geraRelatorio();
				JasperExportManager.exportReportToPdfStream(relatorio, out); 
			} catch (Exception e) {
				
			}
		}
}
