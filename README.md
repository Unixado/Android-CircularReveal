# Android-CircularReveal
![](https://img.shields.io/badge/-Java-informational?style=for-the-badge&logo=Java&logoColor=white&color=007396)

Circular Reveal Animation to go back and forth between android Activities.

<br/>

## Preview 

<p align="center">
  <img height=600 src="https://github.com/Unixado/Android-CircularReveal/blob/master/Preview/Preview.gif">
</p>

## How to use 
1. Add the line bellow to the repositories of your **root** `build.gradle` file: <br/>
`maven { url 'https://jitpack.io' }` 

``` allprojects { 
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
``` 
<br/>


2. Add the following dependency in your **app’s** `build.gradle` file: <br/>
`implementation 'com.github.Unixado:AndroidCircularReveal-Library:Tag'`
```
dependencies {
    implementation 'com.github.Unixado:AndroidCircularReveal-Library:Tag'
    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation 'androidx.appcompat:appcompat:1.2.0'
}
```
3. Use the library <br/>
The opening animation should be run once the layout has been created. This could be done using the [ViewTreeObserver](https://developer.android.com/reference/android/view/ViewTreeObserver).<br/> An example of usage has been created [here](https://github.com/Unixado/AndroidCircularReveal-Library/blob/master/app/src/main/java/unixado/circularrevealanimation/SecondActivity.java). 

## Additional tips
You might face are the following problem when the default white or black android transition screen is being shown instead of the previous activity during the animation.<br/>

<p align=center>
  <img height=500 src="https://github.com/Unixado/AndroidCircularReveal-Library/blob/master/Preview/noTranslucency.gif">
  <img height=500 src="https://github.com/Unixado/AndroidCircularReveal-Library/blob/master/Preview/noTransparency.gif">
</p>

To solve that problem add this following lines to your `styles.xml` default Appstyle:<br/>
```
<item name="android:windowDisablePreview">true</item>
<item name="android:windowIsTranslucent">true</item>
<item name="android:windowBackground">@android:color/transparent</item>
```
