package lolusApp;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String latLng; //Location of event/lamppost using Google Maps latitude/longitude notation
    private String time;   //Time of event

    private Event(){}

    public Event(String latLng, String time){
        this.latLng = latLng;
        this.time = time;
    }

    public String getLocation() {
        return latLng;
    }

    public String getTime() {
        return time;
    }

}
