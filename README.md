# VisiblePercentLayout

VisibilityPercentageLayout is a layout (extends RelativeLayout) with a custom listener which gives the visibility percentage of the layout every time it is changed.Pixel visibility listener is also available.

### Currently supporting
- Use **ONLY** inside Listviews,RecyclerViews,ScrollViews etc.
- Percentage Listener with visible height/width percentage and flags for which part is missing
- Pixels Listener with visible height/width pixels and flags for which part is missing




### Demo

![alt text](http://i268.photobucket.com/albums/jj26/tzanou/simple_zpskny3oz8q.gif ) ![alt text](http://i268.photobucket.com/albums/jj26/tzanou/complex_zpsdvhsxfkn.gif ) ![alt text](http://i268.photobucket.com/albums/jj26/tzanou/horizontal_zpsen0debme.gif )

### Usage



#### In your xml file

```xml
   <tzanou.gr.visiblepercentlayout.PercentVisibleLayout
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_gravity="center"
        android:id="@+id/custom_layout">
        
        <TextView
            android:layout_gravity="center"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/percentage_text"/>
            
    </tzanou.gr.visiblepercentlayout.PercentVisibleLayout>
```
#### In your activity

```java

    mCustomLayout=(PercentVisibleLayout) findViewById(R.id.custom_layout);

    mCustomLayout.setOnVisibilityPercentChangedListener(new PercentVisibleLayout.OnVisibilityPercentChanged() {
            @Override
            public void onVisibilityChange(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {
                holder.mText.setText( percentageHeight+"%" );
            }
        });
```

## More examples and methods on [Wiki Page](https://github.com/tzanou/VisibilityPercentageLayout/wiki)


