# convolution-kernel
My very first coding assignment as an SD student at GMIT, handed in in August 2021.

	NOTE:
	
When compiling the code, please use UTF8 encoding to ensure localized versions are displayed correctly:

javac -encoding UTF8 ie/gmit/dip/*.java

	MAIN FEATURES:
	
This app is a command line menu-driven Java application capable of filtering a PNG image using a convolution kernel.

• Place any PNG image into the src folder.

• Load the image via the app’s main menu.

• Select a filter.

• Apply the filter. The processed image will be saved into the same folder. The file name will be appended by the app title, the most recently applied filter, and the date and time.

• You may apply several filters in a row to the same image. If you want to start over with your original image, please reload it from the main menu.

	ADDITIONAL FEATURES:
	
• Kernels have been organised in an Enum class as suggested.

• Edge handling has been implemented (extend nearest border pixel).

• SRP/Encapsulation: A Menu class has been added, relieving the Runner class from the task of interacting with the user:
<> = “interacts with”

Buffer <> Runner <> Menu <> User

The Runner keeps the app running. In each cycle, it monitors and interacts with the buffer as needed. It then calls the Menu, passing on relevant information, and the Menu provides a user interface based on this information.

• Any user-facing strings have been organised in an Enum class to keep the code clean and allow for some basic localization via the L10n class. To demonstrate this, I have implemented a German version. You can change the language in the main menu.

	REFERENCES:

This app uses code from external sources as commented in the respective files:

• The ConsoleFX.clearConsole method

• The chained call of ZonedDateTime methods in ImageBuffer.appendFileName, lines 45 and 46