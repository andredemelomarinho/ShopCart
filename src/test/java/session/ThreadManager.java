package session;

import session.Session;
import session.Suitesession;

public class ThreadManager {
	private static ThreadLocal<Session> testContext = new ThreadLocal<>();
	private static ThreadLocal<Suitesession> suiteContext = new ThreadLocal<>();

	public static Session getSession() {
		Session s = testContext.get();
//		if (s == null) {
//			s = new Session();
//			session.set(s);
//		}
		return s;
	}

	public static void closeSession() {
		testContext.set(null);
	}

	public static void setSession(Session session) {
		testContext.set(session);
	}

	public static Suitesession getSuiteSession() {
		Suitesession ss = suiteContext.get();
		if (ss == null) {
			ss = new Suitesession();
			suiteContext.set(ss);
		}
		return ss;
	}

	public static void closeSuiteSession() {
		suiteContext.set(null);
	}

}
