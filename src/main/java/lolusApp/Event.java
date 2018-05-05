package lolusApp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String lat;
    private String lng;//Location of event/lamppost using Google Maps latitude/longitude notation
    private String time;   //Time of event

    private Event(){}

    public Event(String lat, String lng, String time){
        this.lat = lat;
        this.lng = lng;
        this.time = time;
    }

    public String getLat() {
        return lat;
    }
    public String getLng(){ return lng; }
    public String getTime() {
        return time;
    }

}
