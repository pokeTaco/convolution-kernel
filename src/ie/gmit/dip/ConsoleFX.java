package ie.gmit.dip;

import java.io.IOException;

/*
 * The ConsoleFX class contains some static methods to do with the console and text formatting
 */

public class ConsoleFX {
	/*
	 * Clear the console (to keep the menu aligned with the top of the
	 * screen/window). Source:
	 * https://www.javatpoint.com/how-to-clear-screen-in-java#Command-Line-
	 * Interpreter
	 */
	public static void clearConsole() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Some text formatting
	 */
	public static String cRed(String s) {
		return ConsoleColour.RED + s + ConsoleColour.RESET;
	}

	public static String cGreen(String s) {
		return ConsoleColour.GREEN + s + ConsoleColour.RESET;
	}

	public static String cBlue(String s) {
		return ConsoleColour.CYAN + s + ConsoleColour.RESET;
	}

	public static String cYellow(String s) {
		return ConsoleColour.YELLOW + s + ConsoleColour.RESET;
	}
	
	public static String ulGreen(String s) {
		return ConsoleColour.GREEN_UNDERLINED + s + ConsoleColour.RESET;
	}

	public static String hlBlue(String s) {
		return ConsoleColour.WHITE_BRIGHT + "" + ConsoleColour.BLUE_BACKGROUND + s + ConsoleColour.RESET;
	}
}
