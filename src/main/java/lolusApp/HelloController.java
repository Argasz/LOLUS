package lolusApp;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;

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

    @GetMapping("/sql")
    String sql() throws IOException, SQLException {

        String url = "jdbc:mysql://mysql.dsv.su.se:3306/jepl4052";
        String username = "jepl4052";
        String password = "eaJohG3ezahm";

        JsonFactory jFactory = new JsonFactory();
        StringWriter sw = new StringWriter();
        JsonGenerator jgen = jFactory.createGenerator(sw);

        Connection conn = DriverManager.getConnection(url, username, password);
        Statement stmt = conn.createStatement();

        stmt.execute("INSERT INTO jepl4052.test (name) VALUES ('sql-programmmet')");

        ResultSet s = stmt.executeQuery("SELECT * FROM jepl4052.test");

        StringBuilder names = new StringBuilder();

        int count = 0;

        jgen.useDefaultPrettyPrinter();

        jgen.writeStartObject();
        jgen.writeArrayFieldStart("users");

        while(s.next()) {

            jgen.writeStartObject();
            jgen.writeNumberField("id", s.getInt("id"));
            jgen.writeStringField("name", s.getString("name"));
            jgen.writeEndObject();

            count++;
        }

        jgen.writeEndArray();

        jgen.writeEndObject();
        jgen.close();

        stmt.close();

        return "Done: " + count + " rows affected. JSON: <pre>" + sw.toString() + "</pre>";

    }
}