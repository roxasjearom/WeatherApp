# WeatherApp

A simple app that shows weather information of different locations. 

This app contains three main pages:
* Splash screen page - shows the logo of the app for a brief period. Each build variant has a different splash screen.
* Weather list page - Shows a list of locations and its weather. This module uses Model-View-ViewModel (MVVM) pattern for displaying the data, which is being observed from LiveData.
* Weather details page - Shows additional weather details for the selected location. This module uses Model-View-Presenter (MVP) pattern for displaying the data.

Libraries used:
* Retrofit (with Gson) - for network request and parsing of JSON response
* RxJava/RxAndroid - for handling network requests with Retrofit
* Lifecycle library - for ViewModel and LiveData usage
* Room - for local persistence
* Glide - for image display
* Event Bus - for event handling
