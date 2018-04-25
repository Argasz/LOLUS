package lolusApp;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;


@RestController
public class HelloController {
  
    @RequestMapping("/hello")
    String hello() {
        return "Hello";
    }

    @RequestMapping("/jens")
    String jens() throws IOException {
        //return "Jens är bäst - ingen protest.";
        JsonFactory jFactory = new JsonFactory();
        StringWriter sw = new StringWriter();
        JsonGenerator jgen = jFactory.createGenerator(sw);
        jgen.writeStartObject();
        jgen.writeStringField("name", "Jens");
        jgen.writeNumberField("age", 13);
        jgen.writeStringField("gender", "hen");
        jgen.writeEndObject();
        jgen.close();
        return sw.toString();
    }

    @RequestMapping("/erik")
    String erik() {
        return "Erik är bättre";
    }
}