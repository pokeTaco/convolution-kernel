package ie.gmit.dip;

/*
 * This enum contains all the user-facing text to keep the code clean. This also
 * allows for some quick and dirty localization. To demonstrate this, I have implemented
 * a German version of the app.
 */

public enum Strings {
	APP_HEADER(
			"***************************************************\n"
					+ "* GMIT - Dept. Computer Science & Applied Physics *\n"
					+ "*                                                 *\n"
					+ "*           Image Filtering System V0.1           *\n"
					+ "*     H.Dip in Science (Software Development)     *\n"
					+ "*                                                 *\n"
					+ "***************************************************\n",
			"***************************************************\n"
					+ "* GMIT - Dept. Computer Science & Applied Physics *\n"
					+ "*                                                 *\n"
					+ "*           Image Filtering System V0.1           *\n"
					+ "*     H.Dip in Science (Software Development)     *\n"
					+ "*                                                 *\n"
					+ "***************************************************\n"),
	TOP_LEVEL_MENU(
			"  " + ConsoleFX.cYellow(" MAIN MENU ") + "\n\n  1) Load Image\n" + "  2) Select a Filter\n"
					+ "  3) Apply Selected Filter\n  4) Change Language\n  5) Quit",
			"  " + ConsoleFX.cYellow(" HAUPTMENÜ ") + "\n\n  1) Bild laden\n" + "  2) Filter auswählen\n"
					+ "  3) Ausgewählten Filter anwenden\n  4) Sprache ändern\n  5) Verlassen"),
	ERROR_BAD_FILE("  Error: Image could not be buffered.\n  Make sure the file exists and is a valid image.",
			"  Fehler: Bild konnte nicht zwischengespeichert werden.\n  Bitte eine gültige Bilddatei laden."),
	ERROR_BUFFER_EMPTY("  Error: Buffer empty. Please load an image.",
			"  Fehler: Zwischenspeicher leer. Bitte ein Bild laden."),
	ERROR_NO_FILTER("  Error: No filter selected. Please select a filter.",
			"  Fehler: Kein Filter ausgewählt. Bitte einen Filter auswählen."),
	ERROR_CONVOLUTION_FAIL("  Error: Convolution failed.", "  Fehler: Konvolution fehlgeschlagen."),
	ERROR_WRITING_FILE("  Error: Buffered image could not be written.",
			"  Fehler: Dateiablage aus Zwischenspeicher fehlgeschlagen."),
	SUCCESS_FILTERED_IMAGE_SAVED("  Success: Filter applied.\n  Buffered image written to disk.",
			"  Erfolg: Filter angewendet.\n  Bilddatei wurde auf Festplatte gespeichert."),
	SUCCESS_IMAGE_BUFFERED("  Success: The image has been buffered.", "  Erfolg: Bild zwischengespeichert."),
	INPUT_NUMBER("\nPlease enter a number from 1 to %d.", "\nBitte eine Zahl zwischen 1 und %d eingeben."),
	INPUT_CURSOR(" >> ", " >> "),
	INPUT_FILENAME("\nPlease enter a valid file name.", "\nBitte einen gültigen Dateinamen eingeben."),
	SHUTDOWN("Shutting down!", "Programm wird beendet!"), DATE_FORMAT("_uuuu-MM-dd_HH-mm-ss", "_uuuu-MM-dd_HH-mm-ss"),
	APP_NAME("GMIT-IFS", "GMIT-IFS"), BUFFERED_IMAGE("Buffered image: ", "Bild im Zwischenspeicher: "),
	PROPERTIES("Properties: ", "Eigenschaften: "), SELECTED_FILTER("Selected filter: ", "Ausgewählter Filter: "),
	LANGUAGE_MENU("  1) English\n" + "  2) Deutsch", "  1) English\n" + "  2) Deutsch"),
	K_IDENTITY("Identity", "Kopie"), K_EDGE_DECECTION("Edge Detection", "Kantenerkennung"),
	K_EDGE_DECECTION_2("Edge Detection 2", "Kantenerkennung 2"), K_LAPLACIAN("Laplacian", "Laplace"),
	K_SHARPEN("Sharpen", "Schärfung"), K_VERTICAL_LINES("Vertical Lines", "Vertikale Linien"),
	K_HORIZONTAL_LINES("Horizontal Lines", "Horizontale Linien"),
	K_DIAGONAL_45_LINES("Diagonal 45 Lines", "Diagonale Linien"), K_BOX_BLUR("Box Blur", "Verwischen"),
	K_SOBEL_HORIZONTAL("Sobel Horizontal", "Sobel horizontal"), K_SOBEL_VERTICAL("Sobel Vertical", "Sobel vertikal");

	private final String en;
	private final String de;

	Strings(String en, String de) {
		this.en = en;
		this.de = de;
	}

	/*
	 * The app itself is ignorant about its language. It requests strings from the
	 * enum using its getter. The getter asks the L10n class for the language
	 * selected by the user and returns the correct translation.
	 */
	public String get() {
		switch (L10n.getLocale()) {
		case "en":
			return this.en;
		case "de":
			return this.de;
		default:
			return this.en;
		}
	}
}