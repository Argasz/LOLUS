package lolusApp;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;

@CrossOrigin
@RestController
public class HelloController {

    @RequestMapping("/hello")
    String hello() {
        return "Hello";
    }

    @GetMapping("/jens")
    String jens() throws IOException {

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