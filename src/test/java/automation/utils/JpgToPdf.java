package automation.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import config.TestConfig;
import session.ThreadManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JpgToPdf {
	
	public JpgToPdf(){
		
	}
	
	 public void criarJpgToPdf(String nomeArquivo) throws DocumentException, MalformedURLException, IOException{
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
	 
	 public void criarJpgToPdfTeste() throws DocumentException, MalformedURLException, IOException{
		    String nomeArquivo ="CarrinhoCompras";	
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
}
