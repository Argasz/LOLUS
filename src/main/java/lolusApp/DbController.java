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
    public @ResponseBody String addEvent(@RequestParam String time, @RequestParam String location){
        Event e = new Event(location, time);
        eventRepository.save(e);


        return "\nEvent added!";
    }

    @CrossOrigin
    @GetMapping("/getAllEvents")
    public @ResponseBody Iterable<Event> getAllEvents (){
        return eventRepository.findAll();
    }

}
