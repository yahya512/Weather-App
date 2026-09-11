# Weather App

An Android **Weather Application** built with **Kotlin**, designed to provide users with detailed weather information based on their selected city.

The application includes weather information, city search, and language settings, with a simple navigation experience using a bottom navigation bar and navigation component.

## Features

* Display detailed weather information
* Search for cities
* Dynamic weather background based on the selected city
* Change application language between English and Arabic
* Bottom navigation between Home, Search, and Settings

## Screens

### 1. Splash 
the splash screen display a short animation while permission of location appear and user choose to accept or deny it 

![Splash Screen](https://github.com/yahya512/Weather-App/blob/37cd4e30e1d6d9519f3eb803fa696e351820a3c9/app/src/main/java/com/example/weatherapp/screenshots/Screenshot_20260901_201348.png)


### 2. permission of location 
here we used AndroidMainfest to take access for ACCESS_COARSE_LOCATION , ACCESS_FINE_LOCATION 
apply fused location provider client to get current location of user 

![permission screen](https://github.com/yahya512/Weather-App/blob/37cd4e30e1d6d9519f3eb803fa696e351820a3c9/app/src/main/java/com/example/weatherapp/screenshots/Screenshot_20260901_201207.png)

### 3. Home

The Home screen displays the **complete weather information** for the selected city.

The screen provides the user with the available weather details in an organized layout.

The background of the screen also changes depending on the selected city, providing a more dynamic and visually relevant weather experience.

![Weather Home](https://github.com/yahya512/Weather-App/blob/37cd4e30e1d6d9519f3eb803fa696e351820a3c9/app/src/main/java/com/example/weatherapp/screenshots/Screenshot_20260901_201232.png)

---

### 4. Search

The Search screen allows users to search for a specific city.

The user enters the city name in an `EditText`, and the application searches for matching cities.

After the results are displayed, the user can select the desired city and return to the Home screen, where the weather information for the selected city is displayed.

```text
Enter City
    ↓
Search
    ↓
Display Results
    ↓
Select City
    ↓
Home
    ↓
Display Weather
```

![Weather Search](https://github.com/yahya512/Weather-App/blob/37cd4e30e1d6d9519f3eb803fa696e351820a3c9/app/src/main/java/com/example/weatherapp/screenshots/Screenshot_20260901_201256.png)

---

### 5. Settings

The Settings screen provides a simple way to change the application's language.

The user can select either **English** or **Arabic** using a `Spinner`, then press the Apply button.

The selected language is saved, the application restarts from the Splash screen, and the saved language is applied before continuing to the Home screen.

```text
Select Language
       ↓
     Apply
       ↓
Save Language
       ↓
Restart App
       ↓
    Splash
       ↓
Apply Language
       ↓
     Home
```

The language selection is stored so that the application can remember the user's choice when it starts again.

![Weather Settings](https://github.com/yahya512/Weather-App/blob/37cd4e30e1d6d9519f3eb803fa696e351820a3c9/app/src/main/java/com/example/weatherapp/screenshots/Screenshot_20260903_004739.png)


![Weather_Setting_Arabic](https://github.com/yahya512/Weather-App/blob/37cd4e30e1d6d9519f3eb803fa696e351820a3c9/app/src/main/java/com/example/weatherapp/screenshots/Screenshot_20260903_004951.png)


## Navigation

The application uses a **Bottom Navigation Bar** to provide access to the main screens:

```text
┌───────────────────────────────────┐
│                                   │
│             Screen                │
│                                   │
├───────────────────────────────────┤
│   Home   │   Search   │ Settings  │
└───────────────────────────────────┘
```

The main destinations are:

* **Home** — Weather information
* **Search** — Search and select cities
* **Settings** — Application language

## Architecture

The application follows **Clean Architecture** and **MVVM** principles.

```text
UI
 ↓
ViewModel
 ↓
UseCase
 ↓
Repository
 ↓
Remote Data Source
 ↓
Weather API
```

This separation keeps the UI independent from the data source and allows the ViewModel to request data without needing to know where that data comes from.

## Search Flow

The Search feature uses a reactive search flow to avoid sending a request for every single character entered by the user.

```text
User types city name
        ↓
    EditText
        ↓
    Debounce
        ↓
Distinct search query
        ↓
    API Request
        ↓
 Search Results
        ↓
  Select City
        ↓
      Home
```

## Localization

The application supports two languages:

* English 🇬🇧
* Arabic 🇪🇬

The selected language is stored and restored when the application starts.

The project uses the AndroidX **Application Locales** approach to apply the selected application language.

## Dynamic Background

The Home screen provides a dynamic background based on the selected city and we use local list as a source.

This allows the visual appearance of the weather screen to change according to the location currently being displayed.

## Technologies

* **Kotlin**
* **Android SDK**
* **XML**
* **MVVM**
* **Clean Architecture**
* **Coroutines**
* **StateFlow**
* **SharedPreferences**
* **Retrofit**
* **OkHttp**
* **Hilt**
* **RecyclerView**
* **Navigation Component**
* **BottomNavigationView**
* **Glide**
* **Git & GitHub**

## Project Structure

```text
Weather App
│
├── splash
│   └── presentation
│
├── weather
│   ├── data
│   ├── domain
│   └── presentation
│
├── search
│   ├── data
│   ├── domain
│   └── presentation
│
├── settings
│   └── presentation
│
└── core
    └── shared components
```

The project follows a feature-oriented structure while keeping the different architectural layers separated where needed. This approach makes the application's main features visible directly from the project structure.

## Purpose

This project was built to practice developing a complete Android application using modern Android development concepts, including Clean Architecture, MVVM, reactive state management, API integration, Navigation Component, localization, sheard preferences, and location-based weather data.
