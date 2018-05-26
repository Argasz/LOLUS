package lolusApp;

import javax.persistence.*;

@Entity
public class Vote {
    @Id
    @GeneratedValue
    private Long vote_id;

    private String type;
    private String userId;

    @ManyToOne
    @JoinColumn(name="event_id", nullable = false)
    private Event event;

    private Vote(){}
    public Vote(String type, String userId, Event event){
        this.type = type;
        this.userId = userId;
        this.event = event;
    }

    public String getType(){ return type;}
    public String getUserId() { return userId;}
    public Event getEvent() {return event;}
}
