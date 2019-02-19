package automation.utils;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import automation.utils.ITestData;
import automation.utils.VerifyDTO;

public abstract class TestData implements ITestData{
	

	private List<VerifyDTO> validations;
	
	@XmlElementWrapper(name="validacoes")
	@XmlElement(name="validacao")
	public List<VerifyDTO> getValidations() {
		return validations;
	}

	public void setValidations(List<VerifyDTO> validations) {
		this.validations = validations;
	}

}
