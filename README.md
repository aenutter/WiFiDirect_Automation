# Basic sample for UiAutomator

* This project was cloned from https://github.com/anvyal/P2P_Automation which uses Perl and UiAutomator to test Wi-Fi Direct
  This project upgraded to uiautomator 2.0 framework "Testing Support Library" and AndroidJUnitRunner from uiautomator.jar
  This project changed to the Gradle build system from the ant build system

This project uses the Gradle build system. You don't need an IDE to build and execute it but Android Studio is recommended.

1. Download the project code, preferably using `git clone`.
2. Open the Android SDK Manager (*Tools* Menu | *Android*) and make sure you have installed the *Android testing support library Repository* under *Extras*.
3. In Android Studio, select *File* | *Open...* and point to the `./build.gradle` file.
4. Check out the relevant code:
    * Tests are in `src/androidTest/java`
5. Connect two Android Lollipop devices (API 21 or 22) (preferably rooted)
6. Start the automation with "perl Start_Automation.pl"