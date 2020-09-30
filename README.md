<a href="https://github.com/CanerGures/TokenFT-DemoApplication/pulls"><img src="https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat" alt="contributions welcome" /></a>
<a href="https://android-arsenal.com/api?level=21"><img src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat" alt="API" /></a>


# TokenFT-DemoApplication MVVM+Repo

Application is made by the purpose of to try payment options. You need to enter the receipt amount to the text box. Then you will get a QR code. You can proceed to payment with that code. In the application, you can also scan the barcode which comes from the backend and then you may select the button "Payment". If the QR code is valid, the transaction will complete. If there is an error, the error will be shown in an alert dialog. 

## Screen Shots📱

<p><img height= "400" src="https://media.giphy.com/media/uQtwUAT0l3nTT38cHF/giphy.gif" alt="Gif1" />
<img height= "400" src="https://media.giphy.com/media/gSPLgiCAeEr5eHJHed/giphy.gif" alt="Gif2" />
<img height= "400" src="https://media.giphy.com/media/UzNtd0pOaodVVs93bY/giphy.gif" alt="Gif3" /></p>

## Libraries and Tools Used🛠 

<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a></li>
<li><a href="https://developer.android.com/topic/libraries/architecture/livedata">LiveData</a></li>
<li><a href="https://developer.android.com/topic/libraries/data-binding">Data Binding</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://github.com/square/okhttp">OkHttp</a></li> 
<li><a href="https://github.com/journeyapps/zxing-android-embedded">QR Scanner</a></li>
<li><a href="https://material.io/develop/android/docs/getting-started/">Material Design</a></li>

## Architecture
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, and a lot more.

![Architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)


