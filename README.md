# Giphy

This application with MVVM Clean Architecture is an example of using such libraries and components as Retrofit, Room, RxJava2, Glide, Navigation, Hilt, LiveData, DataBinding, Viewpager2.


# Task:

Create an app using the giphy REST api https://developers.giphy.com

The application must have two screens.

First screen functionality:  
- Displaying gif images as a list / table  
- Uploading images must be paged  
- Search for images by keyboard input  
- Offline mode - caching images to internal storage and links to images to a local database  
- Ability to delete an image from local storage, while such an image should not be displayed in the list on the screen during subsequent requests to the server  

Functionality of the second screen:  
- Full screen display of the selected GIF  
- Ability to view other images using a horizontal swipe across the screen  
