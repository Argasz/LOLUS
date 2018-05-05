package lolusApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*TODO: Datatyper, måste kunna söka efter intervall av tid/plats i databasen.
 *TODO:      Metoder för detta.*/

@Controller
public class DbController {

    @Autowired
    private EventRepository eventRepository;


    @CrossOrigin
    @GetMapping("/addEvent")
    public @ResponseBody String addEvent(@RequestParam String time, @RequestParam String lat, @RequestParam String lng){
        Event e = new Event(lat, lng, time);
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
        return eventRepository.findAllByTimeBetween(startTime, endTime);
    }

    @CrossOrigin
    @GetMapping("/getEventsByTimeAndLoc")
    public @ResponseBody Iterable<Event> getEventsByTimeAndLoc (@RequestParam String startTime, @RequestParam String endTime,
                                                                @RequestParam String startLat, @RequestParam String endLat,
                                                                @RequestParam String startLng, @RequestParam String endLng){
        return eventRepository.findAllByTimeBetweenAndLatBetweenAndLngBetween(startTime, endTime, startLat, endLat, startLng, endLng);
    }


}
