package java_courses;

import org.eclipse.jetty.server.Server;
import org.flywaydb.core.Flyway;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Start {
    public static final String BASE_URI = "http://localhost:3466/";

    public static Server startServer() {
        final ResourceConfig config = new ResourceConfig(Index.class, ProductController.class);
        return JettyHttpContainerFactory.createServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {
        final Flyway flyway = Flyway
                .configure()
                .dataSource("jdbc:postgresql://localhost/" + CREDS.dbName, CREDS.user, CREDS.password)
                .locations("db")
                .load();

        flyway.migrate();
        System.out.println("Migrations applied successfully");

        final Server server = startServer();
    }

}