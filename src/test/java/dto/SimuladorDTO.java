package dto;



import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import automation.utils.TestData;



@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
@XmlRootElement(name = "ProcessoDTO")
@XmlType(name="formularioDTO", propOrder = {"aplicacao","poupanca","tempo","tipo","periodo"})
public class SimuladorDTO extends TestData {

	private String aplicacao;
	private String poupanca;
	private String tempo;
	private String tipo;
	private String periodo;
			
	@XmlElement(name = "aplicacao")
	public String getAplicacao() {
		return aplicacao;
	}
	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}
	
	@XmlElement(name = "poupanca")
	public String getPoupanca() {
		return poupanca;
	}
	public void setPoupanca(String poupanca) {
		this.poupanca = poupanca;
	}
	
	@XmlElement(name = "tempo")
	public String getTempo() {
		return tempo;
	}
	public void setTempo(String tempo) {
		this.tempo = tempo;
	}
	
	@XmlElement(name = "tipo")
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@XmlElement(name = "periodo")
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
}
