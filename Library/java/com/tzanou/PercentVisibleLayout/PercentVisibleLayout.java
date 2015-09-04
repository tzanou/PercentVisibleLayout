package com.tzanou.PercentVisibleLayout;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

/**
 * Created by tzanou on 8/28/15.
 */
public class PercentVisibleLayout extends RelativeLayout {


    private OnVisibilityPercentChanged mPercentageListener;
    private OnVisibilityPixelChanged mPixelVisibilityListener;
    private String id = "";
    private Context mContext;
    private int lastPixelWidth, lastPixelHeight, lastPercentageWidht, lastPercentageHeight;
    private int minHorizontalPercentage = 0;
    private int maxHorizontalPercentage = 101;
    private int minVerticalPercentage = 0;
    private int maxVerticalPercentage = 101;
    private boolean duplicateEnabled = false;
    public static final int TOP = 1;
    public static final int BOTTOM = 3;
    public static final int RIGHT = 2;
    public static final int LEFT = 4;
    public static final int LEFT_AND_RIGHT = 5;
    public static final int TOP_AND_BOTTOM = 6;
    public static final int NOWHERE = 7;


    public PercentVisibleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.mPercentageListener = null;


        this.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                calculateVisibility();


            }
        });

    }


    public PercentVisibleLayout(Context context, AttributeSet attrs, int defStyleAttr, String id) {
        super(context, attrs, defStyleAttr);

    }

    public PercentVisibleLayout(Context context, AttributeSet attrs, String id) {
        super(context, attrs);

    }

    public PercentVisibleLayout(Context context) {
        super(context);

    }



    @Override
    public void setRotation(float rotation) {
        // super.setRotation(rotation);
    }

    private void calculateVisibility() {


        Rect rectf = new Rect();
        this.getLocalVisibleRect(rectf);



        int fromWhereHeigth;
        int fromWhereWidht;
        int top = rectf.top;
        int bottom = rectf.bottom;
        int right = rectf.right;
        int left = rectf.left;
        int width = this.getWidth();
        int height = this.getHeight();
        int heightPercentage;
        int heightPixels;
        int widthPercentage;
        int widthPixels;


        if (top != 0 && bottom != height) {
            fromWhereHeigth = TOP_AND_BOTTOM;

        } else if (top != 0) {
            fromWhereHeigth = TOP;
        } else if (bottom != height) {
            fromWhereHeigth = BOTTOM;

        } else {
            fromWhereHeigth = NOWHERE;
        }


        if (left != 0 && right != width) {
            fromWhereWidht = LEFT_AND_RIGHT;

        } else if (left != 0) {
            fromWhereWidht = LEFT;
        } else if (right != width) {
            fromWhereWidht = RIGHT;

        } else {
            fromWhereWidht = NOWHERE;
        }


        heightPixels = height + top - bottom;
        widthPixels = width + left - right;

        heightPercentage = (int) (100 - ((double) (heightPixels) / height) * 100);
        widthPercentage = (int) (100 - ((double) (widthPixels) / width) * 100);

        if (top > height || bottom > height) {
            heightPercentage = 0;
            heightPixels = 0;
        }

        if (right > width || left > width) {
            widthPercentage = 0;
            widthPixels = 0;
        }


        if (mPercentageListener != null && isBetweenHorizontalPercentageLimits(widthPercentage) && isBetweenVerticalPercentageLimits(heightPercentage)) {

            if (duplicateEnabled || (!duplicateEnabled && (lastPercentageHeight != heightPercentage || lastPercentageWidht!=widthPercentage))){
                lastPercentageHeight=heightPercentage;
                lastPercentageWidht=widthPercentage;
                mPercentageListener.onVisibilityChange(fromWhereHeigth, fromWhereWidht, heightPercentage, widthPercentage);

            }



        }



            if (mPixelVisibilityListener != null) {
                mPixelVisibilityListener.onVisibilityChange(fromWhereHeigth, fromWhereWidht, heightPixels, widthPixels);
            }






    }


    public void setMinHortizontalPercentage(int perc){
        if(perc>=0 && perc <=100){
            this.minHorizontalPercentage=perc;
        }else{
            Log.d("tzanouError","Sorry not a percentage");
        }

    }


    public void setMaxHorizontalPercentage(int perc){
        if(perc>=0 && perc <=100){
            this.maxHorizontalPercentage=perc;
        }else{
            Log.d("tzanouError","Sorry not a percentage");
        }

    }

    public void setMinVerticalPercentage(int perc){
        if(perc>=0 && perc <=100){
            this.minVerticalPercentage=perc;
        }else{
            Log.d("tzanouError","Sorry not a percentage");
        }

    }


    public void setMaxVerticalPercentage(int perc){
        if(perc>=0 && perc <=100){
            this.maxVerticalPercentage=perc;
        }else{
            Log.d("tzanouError","Sorry not a percentage");
        }

    }


    public void resetPercentageLimits(){
        this.minHorizontalPercentage = 0;
        this.maxHorizontalPercentage = 101;
        this.minVerticalPercentage = 0;
        this.maxVerticalPercentage = 101;

    }

    public void allowDuplicates(boolean bool){
        this.duplicateEnabled=bool;

    }


    private boolean isBetweenHorizontalPercentageLimits(int a){
        if(a<=maxHorizontalPercentage && a>=minHorizontalPercentage){
            return true;
        }else{
            return false;
        }
    }


    private boolean isBetweenVerticalPercentageLimits(int a){
        if(a<=maxVerticalPercentage && a>=minVerticalPercentage){
            return true;
        }else{
            return false;
        }
    }




    public interface OnVisibilityPercentChanged {
        void onVisibilityChange(int verticalClip,int horizontalClip,int percentageHeight,int percentageWidth);
    }

    public void setOnVisibilityPercentChangedListener(OnVisibilityPercentChanged eventListener) {
        mPercentageListener = eventListener;
    }

    public void removeVisibilityPercentageListener() {
        mPercentageListener = null;
    }





    public interface OnVisibilityPixelChanged {
        void onVisibilityChange(int verticalClip,int horizontalClip,int pixelHeight,int pixelWidth);
    }

    public void setOnVisibilityPixelChangedListener(OnVisibilityPixelChanged eventListener) {
        mPixelVisibilityListener = eventListener;
    }

    public void removePixelPercentageListener() {
        mPixelVisibilityListener = null;
    }



}
