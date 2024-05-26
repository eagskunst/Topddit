# Topddit

A mobile application that shows the top post from reddit's https://reddit.com/top. It is also
possible to check the detail of the post and the direct comments of the post.

### Architecture

This applications follows the MVVM + Clean Architecture.

## Requirements
- Java 17
- Gradle 8.6
- Android SDK 28 (min)
- Android SDK 34 (compile/target)
- ADB 

Or just Android Studio Jellyfish | 2023.3.1 Patch 1 or higher versions.

### Compiling

1. Clone the project.
2. Import the project in Android Studio.
3. Sync dependencies
4. Compile with `gradlew build` or with the Run button in your desired device.

## Dependencies

1. Compose: Reactive UI in Android
2. Coil with Fresco: Image loading library.
3. Retrofit and OkHttp: Standard for making http requests on Android.
4. Kotlinx Serialization: Kotlin's standard library for json parsing.
5. Coroutines: For background work

Aside from Retrofit and OkHttp, all dependencies come from either Google or Jetbrains.

## Contributing

I made this app for practice/professional purposes, but PR's are welcome!

### Check the app!

You could download the .apk file from the release tab.