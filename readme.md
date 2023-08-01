# WeatherAppTest

WeatherAppTest is an Android application that allows users to view weather details for a specific location. It fetches weather data from the OpenWeatherMap API and stores it in a local database for offline access. The app is built using Kotlin, Jetpack Compose, Hilt, Room, Retrofit, and Coroutines.

## Features

- View weather details (temperature, date, etc.) for a specific location.
- Fetch weather data from the OpenWeatherMap API.
- Store weather data in a local database for offline access.
- Show loading indicator while fetching data.
- Display error message in case of API errors.

## Installation

To build and run the WeatherAppTest application, you will need Android Studio 4.0 or higher and an Android device or emulator with API level 23 or higher.

1. Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/your-username/WeatherAppTest.git
```

2. Open Android Studio and select "Open an existing Android Studio project."

3. Navigate to the cloned directory and select the `WeatherAppTest` folder.

4. Build the project by clicking on the "Build" menu and then "Make Project."

5. Connect your Android device or start an emulator with API level 23 or higher.

6. Run the app by clicking on the "Run" menu and then "Run 'app'."

## Usage

Upon launching the WeatherAppTest application, you will be presented with the main screen, where you can view the weather details for a specific location.

- The top app bar displays the title "WeatherAppTest" and has a purple background color.
- The main screen shows weather details such as temperature and date for the selected location.
- A floating action button with a purple background and a "Refresh" icon allows you to fetch fresh weather data from the API.
- If you have previously fetched weather data, it will be stored in the local database for offline access. The app will display this data if you launch it without an internet connection.

## Architecture

The WeatherAppTest application follows the MVVM (Model-View-ViewModel) architecture pattern for better separation of concerns and easier unit testing.

- View: The `MainActivity` is responsible for displaying the user interface using Jetpack Compose components.
- ViewModel: The `WeatherDetailsViewModel` handles business logic and data management for the main screen. It communicates with the `WeatherRepository` to fetch weather data from the API and local database.
- Repository: The `WeatherRepositoryImpl` is responsible for managing data sources. It fetches weather data from the OpenWeatherMap API using Retrofit and stores it in the local database using Room.
- Local Database: The local database is implemented using Room, and it stores the weather details in the `WeatherDetailsResponse` table.

## Dependencies

The WeatherAppTest app relies on the following libraries and dependencies:

- Kotlin: The programming language used for Android app development.
- Jetpack Compose: The UI toolkit for building modern and reactive user interfaces.
- Hilt: Dependency injection framework for Android.
- Retrofit: HTTP client for making API calls.
- Room: Database library for storing and managing local data.
- Coroutines: For handling asynchronous tasks and managing concurrency.

## Contribution

Contributions to the WeatherAppTest project are welcome! If you find any bugs, have feature requests, or want to contribute improvements, feel free to open an issue or submit a pull request.

## License

The WeatherAppTest project is open-source and available under the MIT License. See the `LICENSE` file for more details.