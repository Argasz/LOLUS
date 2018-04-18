package lolusApp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    String hello() {
        return  "Hello";
    }

    @RequestMapping("/jens")
    String jens() {
        return "Jens är bäst - ingen protest.";
    }

    @RequestMapping("/erik")
    String erik() {return "Erik är bättre";}
