[![](https://jitpack.io/v/nicotorassa/Dynamic-Loading-View.svg)](https://jitpack.io/#nicotorassa/Dynamic-Loading-View)
# Dynamic Loading View

## Import the library to your project

**Step 1**. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
**Step 2**. Add the dependency
```java
dependencies {
        implementation 'com.github.nicotorassa:Dynamic-Loading-View:0.1.2'
}
```

## Usage

**Step 1**. Edit your .xml
```xml
<com.artificioo.dynamicloadingview.view.DynamicLoadingView
    android:id="@+id/dynamic_background"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/coffee" />
```

**Step 2**. Set icons and colors
```java
int[] colors = {
        R.color.white
};
int[] icons = {
        R.drawable.ic_coffee_breaks,
        R.drawable.ic_coffee_cup,
        R.drawable.ic_coffee_cup_2,
        R.drawable.ic_coffee_capsules};
dynamicLoadingView.setColors(colors);
dynamicLoadingView.setIcons(icons);
```
**Step 3**. Start the animation
```java
dynamicLoadingView.start();
```

## Result
![Coffee Loading](screenshots/coffee-loading.gif)
