package xyz.bnayagrawal.android.icontest;

/**
 * Created by root on 2/10/17.
 */

public class EWDataSet {
    private String title;
    private int image; //currently the image resource will be used, so int. later change to string for storing url.
    private String description;
    private String notification_date;
    private String peoples_interested;
    private String peoples_going;
    private String caption;
    private String date;
    private String venue;

    public EWDataSet(String title, int image, String description, String notification_date, String peoples_interested, String peoples_going, String caption, String date, String venue) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.notification_date = notification_date;
        this.peoples_interested = peoples_interested;
        this.peoples_going = peoples_going;
        this.caption = caption;
        this.date = date;
        this.venue = venue;
    }

    //getter methods
    public String getTitle() {return this.title;}
    public int getImage() {return this.image;}
    public String getDescription() {return this.description;}
    public String getNotification_date() {return this.notification_date;}
    public String getPeoples_interested() {return this.peoples_interested;}
    public String getPeoples_going() {return this.peoples_going;}
    public String getCaption() {return this.caption;}
    public String getDate() {return this.date;}
    public String getVenue() {return this.venue;}
}
