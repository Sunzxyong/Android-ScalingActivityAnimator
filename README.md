# Android-ScalingActivityAnimator
An interesting pop-view Animation,This is a popWindow pop-up, activity shrink animation

---
##Effect
![](http://img.my.csdn.net/uploads/201508/22/1440213866_6646.gif)

---
##Uasge
###You can use this to display the default effect.
You must pass the current `activity layout id` and the `pop-view view id`
```java
  ScalingActivityAnimator mScalingActivityAnimator = new ScalingActivityAnimator(this, this, R.id.root_view, R.layout.pop_view);
  mScalingActivityAnimator.start();
```
If you want to get the pop-view child controls, so you can use
```java
  ScalingActivityAnimator mScalingActivityAnimator = new ScalingActivityAnimator(this, this, R.id.root_view, R.layout.pop_view);
  View popView = mScalingActivityAnimator.start();
  Button mButtonBack = (Button) popView.findViewById(R.id.btn_cancel);
  mButtonBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mScalingActivityAnimator.resume();
        }
  });
  
```
`Exit Animation`
```java
  mScalingActivityAnimator.resume();
```
###You can also set the size of the pop-view
```java
  mScalingActivityAnimator.setPopViewHeightIsTwoThirdOfScreen();
```
###Setting styles and adding animation xml file
Add animation xml file in anim directory,and setting styles
```xml
  <style name="showScalingAnimation" parent="android:Animation">
        <item name="android:windowEnterAnimation">@anim/show_popup_in</item>
        <item name="android:windowExitAnimation">@anim/show_popup_out</item>
  </style>
```
