# TodoCompose

A simple Android application built with Kotlin and Jetpack Compose using the MVVM architecture. The app displays a list of tasks.

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
./gradlew assembleDebug
./gradlew installDebug
```

The app should install and launch on the connected device or emulator.

## Project Structure

- `app`: Android application module
  - `ui`: Compose UI and ViewModel implementations
  - `data`: Simple data classes

The UI is implemented with Jetpack Compose. `TaskViewModel` exposes a `StateFlow` of tasks. `TaskListScreen` collects this flow and displays the items in a `LazyColumn`.

## License

This project is provided for demonstration purposes.
