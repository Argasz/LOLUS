package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
	@RequestMapping("/")
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}