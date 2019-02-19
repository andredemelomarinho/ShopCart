package session;

import session.Suitesession;

public class SuiteConfig {

	private static Suitesession suiteSession = new Suitesession();


	public static Suitesession getSuiteSession() {
		
		if (suiteSession == null) {
			suiteSession = new Suitesession();
		}
		return suiteSession;
	}

	public static void fechaSuiteSession() {
		suiteSession = null;
	}
}
