package lolusApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



@RestController
public class DbController {
    final String[] TYPES ={"Okänd","Pistolskott", "Rån", "Bilstöld", "Olycka", "Explosion", "Misshandel"};

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private VoteRepository voteRepository;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");



    @CrossOrigin
    @GetMapping("/addEvent")
    public @ResponseBody String addEvent( @RequestParam String lat, @RequestParam String lng){

        Event e = new Event(lat, lng, new Date());
        eventRepository.save(e);

        return "\nEvent added!";
    }

    @CrossOrigin
    @GetMapping("/getAllEvents")
    public @ResponseBody Iterable<Event> getAllEvents (){
        return eventRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/getEventsByLocation")
    public @ResponseBody Iterable<Event> getEventsByLocation (@RequestParam String startLat, @RequestParam String endLat,
                                                              @RequestParam String startLng, @RequestParam String endLng){
        return eventRepository.findAllByLatBetweenAndLngBetween(startLat,endLat,startLng,endLng);
    }

    @CrossOrigin
    @GetMapping("/getEventsByTime")
    public @ResponseBody Iterable<Event> getEventsByTime (@RequestParam String startTime, @RequestParam String endTime){
        Date start, end;
        start = formatDate(startTime);
        end = formatDate(endTime);
        return eventRepository.findAllByTimeBetween(start, end);
    }

    @CrossOrigin
    @GetMapping("/getEventsByTimeAndLoc")
    public @ResponseBody Iterable<Event> getEventsByTimeAndLoc (@RequestParam String startTime, @RequestParam String endTime,
                                                                @RequestParam String startLat, @RequestParam String endLat,
                                                                @RequestParam String startLng, @RequestParam String endLng){
        Date start, end;
        start = formatDate(startTime);
        end = formatDate(endTime);

        return eventRepository.findAllByTimeBetweenAndLatBetweenAndLngBetween(start, end, startLat, endLat, startLng, endLng);
    }

    @CrossOrigin
    @GetMapping("/addVote")
    public @ResponseBody String addVote(@RequestParam String type, @RequestParam String userToken,
                                        @RequestParam String eventTime, @RequestParam String eventLat,
                                        @RequestParam String eventLng){
        Date time;
        time = formatDate(eventTime);
        Event event;
        event = eventRepository.findEventByTimeAndLatAndLng(time, eventLat, eventLng);
        if(event == null){
            return "No such event";
        }
        boolean found = false;
        for(String s : TYPES){
            if(type.equals(s)){
                found = true;
            }
        }
        if(!found){
            return "No such type.";
        }
        Vote v = new Vote(type,userToken,event);
        voteRepository.save(v);
        return "Vote saved";
    }

    @CrossOrigin
    @GetMapping("/getAllVotes")
    public @ResponseBody Iterable<Vote> getAllVotes(){
        return voteRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/getVotesByEvent")
    public @ResponseBody Iterable<Vote> getVotesByEvent(@RequestParam String eventTime, @RequestParam String eventLat,
                                                        @RequestParam String eventLng){
        Event e = eventRepository.findEventByTimeAndLatAndLng(formatDate(eventTime), eventLat, eventLng);
        if(e == null){
            System.out.println("No such event");
            return null;
        }
        return voteRepository.findAllByEvent(e);
    }

    @CrossOrigin
    @GetMapping("/countVotes")
    public @ResponseBody String getTypeByEvent(@RequestParam String eventTime, @RequestParam String eventLat,
                                                                 @RequestParam String eventLng){
        String type = "Okänd";
        Event e = eventRepository.findEventByTimeAndLatAndLng(formatDate(eventTime),eventLat,eventLng);
        Long count = 0L;
        for(String s : TYPES){
            Long evCount = voteRepository.countAllByTypeAndEvent(s,e);
            if(evCount > count){
                count = evCount;
                type = s;
            }
        }
        return type;
    }

    private Date formatDate(String date){
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            Date rd = new Date();
            rd.setTime(0);
            return rd;
        }

    }

    @CrossOrigin
    @GetMapping("/getPolice")
    public @ResponseBody String getEventsByTimeAndLoc (@RequestParam String locationName) throws IOException {

        String urlToRead = "https://polisen.se/api/events?locationName=" + locationName;
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }
}
