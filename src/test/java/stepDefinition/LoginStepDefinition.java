package stepDefinition;

import automation.Pages;
import automation.utils.ArquivoUtils;
import cucumber.api.java.en.Given;
import session.ThreadManager;
import dto.SimuladorDTO;

import org.apache.log4j.Logger;

import Enum.Login;
import automation.Sicredi;

public class LoginStepDefinition extends Sicredi {
	
	Login login = null;
	SimuladorDTO processoDTO = null;
	
	private Pages getPages() {
		return ThreadManager.getSession().getPages();
	}
	final static Logger logger = Logger.getLogger(LoginStepDefinition.class);
	private void setupTest(String sys) {
		try {
			setUpSuite();
			setUpClass(sys);
			setUpMethod();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Given("^Acessar o site de compras$") 
	public void acesso_site_Sicredi() throws Throwable {
	   		setupTest("");
	   		ArquivoUtils.tiraScreenshot("COMPRAS.jpg");
			logger.info("Acessando site Carrinho ");
		}
	

}
