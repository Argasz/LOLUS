package lolusApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DbController {

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/addEvent")
    public @ResponseBody String addEvent(@RequestParam String time, @RequestParam String location){
        //{year:"",month:"",day:"", hour:"", minute:""}
        //{lat:xx.xx, long:yy.yy}
        Event e = new Event(location, time);
        eventRepository.save(e);

        //JSONIFY

        return "Event added!";
    }

}
