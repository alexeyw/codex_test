# TodoCompose

A simple Android application built with Kotlin and Jetpack Compose using the MVVM architecture. The app now persists tasks using Room and provides full CRUD functionality. Authentication is handled with Firebase allowing users to sign up and log in before accessing their tasks.

## Requirements

- Android Studio Hedgehog (or newer)
- Android SDK 34
- Java 17+

## Building and Running

1. Clone this repository.
2. Open the project in Android Studio.
3. Let Gradle sync the project. Internet access is required on the first build to download dependencies.
4. Connect an Android 12+ device or start an emulator.
5. Click **Run** or execute:

```bash
gradle assembleDebug
gradle installDebug
```

The app should install and launch on the connected device or emulator.

## Project Structure

- `app`: Android application module
  - `data`: Room entities, database and repository
  - `ui`: Compose UI and ViewModel implementations
  - `auth`: Firebase Authentication helpers

Tasks are stored locally in a Room database. `TaskViewModel` exposes a `StateFlow` of tasks which updates automatically when changes occur. `TaskListScreen` displays the list and navigation allows adding or editing tasks.

## License

This project is provided for demonstration purposes.
