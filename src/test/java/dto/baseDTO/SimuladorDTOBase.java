package dto.baseDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.RandomStringUtils;

//import org.apache.commons.lang3.RandomStringUtils;

import dto.SimuladorDTO;


public class SimuladorDTOBase {

	public static SimuladorDTO getSimuladorBaseRealizado() {
		SimuladorDTO simulador = new SimuladorDTO();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		simulador.setAplicacao("20,00");	
		simulador.setPoupanca("20,00");
		simulador.setTempo("1");
		simulador.setTipo("associado");
		simulador.setPeriodo("M");
		
		return simulador;
	}
	
}
