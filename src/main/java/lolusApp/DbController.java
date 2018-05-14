package lolusApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class DbController {

    @Autowired
    private EventRepository eventRepository;

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
}
