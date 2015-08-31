# VisibilityPercentageLayout

VisibilityPercentageLayout is a layout (extends RelativeLayout) with a custom listener which gives the visibility percentage of the layout every time it is changed.Pixel visibility listener is also available.

### Demo

![alt text](http://i268.photobucket.com/albums/jj26/tzanou/simple_zpsklounw4l.gif )

### Usage



#### In your xml file

```xml
   <tzanou.gr.visibilitypercentage.PercentageVisibilityLayout
        android:layout_width="300dp"
        android:layout_height="300dp"
        android:layout_gravity="center"
        android:id="@+id/custom_layout"
        >
        <TextView
            android:layout_gravity="center"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/percentage_text"/>
    </tzanou.gr.visibilitypercentage.PercentageVisibilityLayout>
```
#### In your activity

```java

    mCustomLayout=(PercentageVisibilityLayout) findViewById(R.id.custom_layout);

    mCustomLayout.setPercentageVisibilityListener(new PercentageVisibilityLayout.PercentageVisibilityListener() {
            @Override
            public void onEvent(int fromHeight, int fromWidth, int percentageHeight, int percentageWidth) {
                holder.mText.setText( percentageHeight+"%" );
            }
        });
```

## More examples and methods on [Wiki Page](https://github.com/tzanou/VisibilityPercentageLayout/wiki)


