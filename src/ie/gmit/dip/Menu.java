package ie.gmit.dip;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Menu {
	/*
	 * Prompt the user to enter a number between 1 and max (for navigating a menu
	 * with a set number of options.) Loop while the user has not made a valid
	 * selection
	 */
	private static int getNavChoice(int max) {
		int input = 0;
		while (input < 1 || input > max) {
			System.out.println(String.format(Strings.INPUT_NUMBER.get(), max));
			System.out.print(Strings.INPUT_CURSOR.get());
			Scanner console = new Scanner(System.in);
			try {
				input = console.nextInt();
			} catch (Exception e) {
				getNavChoice(max);
			}
		}
		return input;
	}

	/*
	 * Prompt the user to enter the file name of the image they want to buffer
	 */
	private static String getFileName() {
		System.out.println(Strings.INPUT_FILENAME.get());
		System.out.print(Strings.INPUT_CURSOR.get());
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}

	/*
	 * Display the main menu and prompt the user to make a selection
	 */
	private static void navigateTopLevel() {
		System.out.println(Strings.TOP_LEVEL_MENU.get());
		switch (getNavChoice(5)) {
		case 1: // Load Image
			Runner.loadImage(getFileName());
			break;
		case 2: // Select a Filter
			navigateFilters();
			break;
		case 3: // Apply Selected Filter
			Runner.applyFilter();
			break;
		case 4: // Change Language
			navigateLanguages();
			break;
		case 5: // Quit
			Runner.quit();
			break;
		default:
			navigateTopLevel();
		}
	}

	/*
	 * Return a numbered list of all the available kernels from the enum
	 */
	private static String getAvailableFilters() {
		StringBuffer list = new StringBuffer();
		int index = 1;
		for (Kernel kernel : Kernel.values()) {
			list.append("  " + index + ") " + kernel + "\n");
			index++;
		}
		return "\n" + list.toString();
	}

	/*
	 * Display the list of available kernels and prompt the user to make a
	 * selection
	 */
	private static void navigateFilters() {
		System.out.print(getAvailableFilters());
		Runner.setKernel(getNavChoice(Kernel.values().length) - 1);
	}
	
	/*
	 * Display the language menu and prompt the user to make a selection
	 */
	private static void navigateLanguages() {
		System.out.println(Strings.LANGUAGE_MENU.get());
		switch (getNavChoice(2)) {
		case 1: // English
			L10n.setLocale("en");
			break;
		case 2: // German
			L10n.setLocale("de");
			break;
		default:
			navigateTopLevel();
		}
	}
	
	/*
	 * Show the main menu
	 */
	public static void show(String event, String buffer, String kernel) {
		// Clear the console
		ConsoleFX.clearConsole();

		// Print the app header
		System.out.println(ConsoleFX.hlBlue(Strings.APP_HEADER.get()));

		// Report on any operation success/failure
		if (event != null) {
			System.out.println(event + "\n");
		}

		// If the buffer holds an image, display some useful information
		if (buffer != "none") {
			System.out.println(buffer);
		}

		// Display any selected filter
		if (kernel != "none") {
			System.out.println(kernel + "\n");
		}

		// Prompt the user to make a selection
		navigateTopLevel();
	}
}
