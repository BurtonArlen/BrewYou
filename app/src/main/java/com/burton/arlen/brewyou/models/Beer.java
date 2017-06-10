package com.burton.arlen.brewyou.models;

import org.parceler.Parcel;

/**
 * Created by arlen on 6/2/17.
 */

@Parcel
public class Beer {
    private String name;
    private String id;
    private String imageIcon;
    private String imageMedium;
    private String imageLarge;
    private String style;
    private String availability;
    private String google;
    private String opinion;
    private String pushId;
    private String descripton;

    public Beer(){}

    public String getName() { return name; }

    public String getId() { return id; }

    public String getImageIcon() { return imageIcon; }

    public String getImageMedium() { return imageMedium; }

    public String getImageLarge() { return imageLarge; }

    public String getStyle() { return style; }

    public String getAvailability() { return availability; }

    public String getGoogle(){ return google; }

    public String getPushId() { return pushId; }

    public void setPushId(String pushId) { this.pushId = pushId; }

    public String hateBeer() { return opinion = "hate"; }

    public String likeBeer() { return opinion = "like"; }

    public String isOpinion() { return opinion;}

    public String getDescripton() { return descripton; }

    public Beer(String name, String id, String imageIcon, String imageMedium, String imageLarge, String style, String availability, String description){
        this.name = name;
        this.id = id;
        this.imageIcon = imageIcon;
        this.imageMedium = imageMedium;
        this.imageLarge = imageLarge;
        this.style = style;
        this.availability = availability;
        this.google = "https://www.google.com/search?q=" + name;
        this.descripton = description;
    }
}
