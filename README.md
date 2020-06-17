# Weather_App

https://github.com/jasotharan/Weather_App.git


This an Android application using kotlin to show  weather forecast of 5 days.
Im using "retrofit" to forecast weather, using the OpenWeatherMap 5 day weather forecast API.
(https://openweathermap.org/forecast5)

  In this application implemented in two way to forecast 5 days weather information
    1.  Using latitude and longitude coordinates (from device location ) to get 5 days of weather forecast
        eg- http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid="your_api_key"

    2.  Using city or county name to get 5 days of weather forecast information
        eg- http://api.openweathermap.org/data/2.5/forecast?q=jaffna,LK&appid=e201f96da1403d6b12ea825bcc60233c

    note : -
     #   Both of above mentioned methods retrieve weather forecast of 5 days at an interval of 3 hours.
         ie: 24/3=8 Times per day and all together 8*5=40 Numbers of forecasting information will available.

         Detailed information of first forecast will be shown in the main view as default and rest will be shown
         in the recycler view appearing in the bottom.

         Recycler view can be scroll horizontally and see remaining forecasts. By clicking the desired
         forecast it can be seen in the main view.

     #   Back ground will be changed to dark or bright with respect to the time period of required weather information.
         ie: if night back ground theme will be dark and if day the theme will be bright.
         It can be modified further specifically in future by considering both time and weather condition
            (I have mentioned this in the code as TODO)

     #   here used the live data and Data Binding Library works seamlessly with ViewModel, which expose the data that
         the layout observes and reacts to its changes

     #   In the above mentioned method, the specific location set as singapore coordinates
         get device coordinates using gps or other methods are not captured here.
            (I have mentioned this in the code as TODO)


 ** This project can be download form above mention link  **