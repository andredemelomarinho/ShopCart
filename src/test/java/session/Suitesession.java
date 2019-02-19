package session;


import java.util.HashMap;
import java.util.Map;


public class Suitesession {
	private Map<String, String> numerosProcessos;
	private String ambiente;


	private String testName;
	
	static {
	}

	Suitesession() {
		numerosProcessos = new HashMap<String, String>();
	}
	
	
	public Map<String, String> getNumerosProcessos() {
		return numerosProcessos;
	}

	public void setNumerosProcessos(String numeroProcesso) {
		numerosProcessos.put(numeroProcesso, "");
	}
	
	public void alterarResultadoProcesso(String numeroProcesso, String resultado) {
		if(!numeroProcesso.equals("")){
			numerosProcessos.put(numeroProcesso, resultado);
		}
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

}
