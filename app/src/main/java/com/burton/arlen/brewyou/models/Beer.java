package com.burton.arlen.brewyou.models;

/**
 * Created by arlen on 6/2/17.
 */

public class Beer {
    private String mName;
    private String mId;
    private String mImageIcon;
    private String mImageMedium;
    private String mImageLarge;
    private String mStyle;
    private String mAvailability;
    private String mGoogle;

    public Beer(){}

    public String getmName() {
        return mName;
    }

    public String getmId() {
        return mId;
    }

    public String getmImageIcon() {
        return mImageIcon;
    }

    public String getmImageMedium() {
        return mImageMedium;
    }

    public String getmImageLarge() {
        return mImageLarge;
    }

    public String getmStyle() {
        return mStyle;
    }

    public String getmAvailability() {
        return mAvailability;
    }

    public String getmGoogle(){
        return mGoogle;
    }

    public Beer(String name, String id, String imageIcon, String imageMedium, String imageLarge, String style, String availability){
        this.mName = name;
        this.mId = id;
        this.mImageIcon = imageIcon;
        this.mImageMedium = imageMedium;
        this.mImageLarge = imageLarge;
        this.mStyle = style;
        this.mAvailability = availability;
        this.mGoogle = "https://www.google.com/search?q=" + name;
    }
}
