package java_courses;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;

@Path("/")
public class Index {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getIndex() {
        try {
            var path = Index.class.getResource("/index.html");
            assert path != null;
            File f = new File(path.toURI());
            return new FileInputStream(f);
        } catch (FileNotFoundException e) {
            return null;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
