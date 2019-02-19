package automation.utils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


import automation.utils.*;

public class VerifyDTO implements Iverify {
	public VerifyDTO(String name, IVerifyMethod verifyMethod, String expectedValue,String option) {
		super();
		this.name = name;
		this.verifyMethod = verifyMethod;
		this.expectedValue = expectedValue;
//		this.resultValue = resultValue;
		this.option = option;
//		this.dependency = dependency;
//		this.testMethod = testMethod;
	}
	public VerifyDTO() {
		super();
	}
	private String name;
	private IVerifyMethod verifyMethod;
	private String expectedValue;
	private String resultValue;
	private String dependency;
	private String testMethod;
	private String option;
	
	@XmlElement(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//	@XmlElement(name = "verifyMethod",nillable=true)
	@XmlTransient
	public IVerifyMethod getVerifyMethod() {
		return verifyMethod;
	}
	public void setVerifyMethod(IVerifyMethod method) {
		this.verifyMethod = method;
	}
	
	@XmlElement(name = "expectedValue")
	public String getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}
	
	@XmlElement(name = "resultValue")
	public String getResultValue() {
		return resultValue;
	}
	public void setResultValue(String resultValue) {
		this.resultValue = resultValue;
	}
	
	@XmlElement(name = "dependency")
	public String getDependency() {
		return dependency;
	}
	public void setDependency(String dependency) {
		this.dependency = dependency;
	}
	
	@XmlElement(name = "testMethod")
	public String getTestMethod() {
		return testMethod;
	}
	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}
	
	@XmlElement(name = "option")
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
}
