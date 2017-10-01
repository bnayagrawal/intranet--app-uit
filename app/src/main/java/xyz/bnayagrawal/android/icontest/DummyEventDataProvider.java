package xyz.bnayagrawal.android.icontest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 1/10/17.
 */

public class DummyEventDataProvider {

    public List<EventDataSet> events;

    //defualt constructor for initialization

    public DummyEventDataProvider() {
        events = new ArrayList<EventDataSet>();
    }

    /**
     * add new event
     *
     * @param title
     * @param image
     * @param description
     * @param notification_date
     * @param peoples_interested
     * @param peoples_going
     * @param caption
     */

    public void add(String title, int image, String description, String notification_date, String peoples_interested, String peoples_going, String caption) {
        events.add(new EventDataSet(title,image,description,notification_date,peoples_interested,peoples_going,caption));
    }

    class EventDataSet {
        private String title;
        private int image; //currently the image resource will be used, so int. later change to string for storing url.
        private String description;
        private String notification_date;
        private String peoples_interested;
        private String peoples_going;
        private String caption;

        public EventDataSet(String title, int image, String description, String notification_date, String peoples_interested, String peoples_going, String caption) {
            this.title = title;
            this.image = image;
            this.description = description;
            this.notification_date = notification_date;
            this.peoples_interested = peoples_interested;
            this.peoples_going = peoples_going;
            this.caption = caption;
        }

        //getter methods
        public String getTitle() {return this.title;}
        public int getImage() {return this.image;}
        public String getDescription() {return this.description;}
        public String getNotification_date() {return this.notification_date;}
        public String getPeoples_interested() {return this.peoples_interested;}
        public String getPeoples_going() {return this.peoples_going;}
        public String getCaption() {return this.caption;}
    }
}
