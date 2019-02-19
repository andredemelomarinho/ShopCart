package automation.utils;

import automation.utils.IVerifyMethod;

public interface Iverify {
	public String getName();
	public IVerifyMethod getVerifyMethod();
	public String getExpectedValue();
	public String getResultValue();
	public void setResultValue(String resultValue);
	public String getDependency();
	public String getTestMethod();
	public String getOption();

}
