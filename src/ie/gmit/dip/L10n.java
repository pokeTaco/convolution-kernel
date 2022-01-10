package ie.gmit.dip;

/*
 * The L10n class knows the currently selected locale (default is English).
 * Its setter is used by the language selection menu. Its getter is used
 * by the string getter method in Strings.java
 */

public class L10n {
	private static String locale = "en";

	public static String getLocale() {
		return locale;
	}

	public static void setLocale(String locale) {
		L10n.locale = locale;
	}
}