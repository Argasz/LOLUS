package lolusApp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long event_id;

    private String lat;
    private String lng;//Location of event/lamppost using Google Maps latitude/longitude notation

    @Temporal(TemporalType.TIMESTAMP) private Date time;   //Time of event

    private Event(){}
    public Event(String lat, String lng, Date time){
        this.lat = lat;
        this.lng = lng;
        this.time = time;
    }

    public String getLat() {
        return lat;
    }
    public String getLng(){ return lng; }
    public Date getTime() {
        return time;
    }
    public Long getEvent_id() { return event_id; }


}
