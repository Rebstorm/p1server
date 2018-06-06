import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import util.StartLoggingUtil;

@SpringBootApplication
@ComponentScan(basePackages="endpoints")
public class Main {


    private static Logger log = LoggerFactory.getLogger(Main.class.getName());


    @Value("${local.server.port}")
    private static String port;

    @Value("${local.server.address}")
    private static String addr;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);

        StartLoggingUtil log = new StartLoggingUtil("Main");
        log.startLogging();
    }



}
