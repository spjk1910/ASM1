
Here's a README template that you can use for your Android app, "Teach Tease Quiz Bot," based on the code you've shared:

Teach Tease Quiz Bot
Overview
"Teach Tease Quiz Bot" is an educational quiz app designed to help users enhance their knowledge of data structures and algorithms in computer science. The user is challenged to guess the correct answer using the hints.

The app allows customization of the user experience, including language preferences, theme (light or dark mode), and background music. It features immersive navigation and a user-friendly interface for a seamless learning experience.

Features
Multiple Languages: Support for English and Vietnamese. The app allows users to change the language dynamically in the settings.
Dynamic Themes: Light and dark modes are available, with the app adjusting its appearance based on user preferences.
Background Music: Optional background music that plays while using the app, enhancing the atmosphere during the quiz game.
Interactive Quiz: The quiz provides up to three hints to help users guess the correct algorithm or data structure. The user advances to the next question if the answer is correct or loses if the maximum hints are used.
Settings: Users can customize their preferences for language, theme, and music from the settings screen.
Installation
To install and run the app, follow these steps:

Prerequisites
Android Studio (version 4.1 or higher)
An Android device or emulator for testing
Steps
Clone this repository:

bash
Copy code
git clone https://github.com/spjk1910/ASM1.git
Open the project in Android Studio.

Build and run the project on an Android device or emulator.

Ensure that all required dependencies are installed (such as MediaPlayer for background music).

Usage
Welcome Screen:

The app begins with a welcome screen that provides options to start the quiz, access settings, or quit the app.
Start: Begin the quiz game.
Settings: Customize language, theme, and music preferences.
Quit: Close the app.
Quiz Game:

In the quiz view, the user is presented with a question related to data structures or algorithms.
The bot provides hints, and the user guesses the correct answer. If the answer is correct, they move to the next question.
Settings:

The settings screen allows the user to select the language, theme, and music preference.
Language options include English and Vietnamese.
Theme options include Light and Dark mode.
Background music can be toggled on/off.

Configuration
Language: Languages are set via the settings screen. Supported languages: English (en), Vietnamese (vi).
Themes: Light mode (AppCompatDelegate.MODE_NIGHT_NO) and Dark mode (AppCompatDelegate.MODE_NIGHT_YES).
Music: Background music (jingle bells) is played if enabled.

Known Issues
Ui may have problems solving correct Dark Theme or Light Theme, sometimes it affects the Language, need to set again to reset

Acknowledgments
https://youtu.be/ENK4ONrRm8s?si=YabbuZi5P98LRP12 for UI of GuessView

This is a demo of app: shared with RMIT account
https://rmiteduau-my.sharepoint.com/:v:/g/personal/s4027648_rmit_edu_vn/Ee911OJ8vGtIu2dsj82sBRoBHTMsrn8S2YlQsBMvuxrDZA?nav=eyJyZWZlcnJhbEluZm8iOnsicmVmZXJyYWxBcHAiOiJPbmVEcml2ZUZvckJ1c2luZXNzIiwicmVmZXJyYWxBcHBQbGF0Zm9ybSI6IldlYiIsInJlZmVycmFsTW9kZSI6InZpZXciLCJyZWZlcnJhbFZpZXciOiJNeUZpbGVzTGlua0NvcHkifX0&e=Blu692
