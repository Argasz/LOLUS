package lolusApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @RequestMapping(value="/hello", method=RequestMethod.GET)
    String hello(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin:", "*");
        return  "Hello";
    }

    @RequestMapping("/jens")
    String jens() {
        return "Jens är bäst - ingen protest.";
    }
}
