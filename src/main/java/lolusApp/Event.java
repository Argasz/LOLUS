package lolusApp;


import javax.persistence.Temporal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TemporalType;
import java.sql.Timestamp;
import java.util.Date;


@Entity
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String lat;
    private String lng;//Location of event/lamppost using Google Maps latitude/longitude notation

    @Temporal(TemporalType.TIMESTAMP) private Date time;   //Time of event

    private Event(){}
    //TODO: Separera tid och datum
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

}
