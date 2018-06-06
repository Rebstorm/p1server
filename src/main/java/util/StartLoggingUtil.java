package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.stereotype.Service;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/*
    This is a service according to Spring Boot.
    That means it pertains or provides business logic, or any other useful logic to the application.
 */

@Service
public class StartLoggingUtil {

    Logger log;

    public StartLoggingUtil(String name){
        log = LoggerFactory.getLogger(name);
    }

    public void startLogging(){
        try {
            Map<String, String> serverSettings = getIpAddressAndPort();

            log.info("=== Logging has started on:\t" +
                    serverSettings.get("address") + ":" +
                    serverSettings.get("port") + " ==="
            );

        } catch(Exception e){
            log.error(e.getMessage());
        }

    }



    public Map<String, String> getIpAddressAndPort()
            throws MalformedObjectNameException, NullPointerException,
            UnknownHostException {

        Map<String, String> map = new HashMap<>();

        MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();

        Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));

        String host = InetAddress.getLocalHost().getHostAddress();
        String port = objectNames.iterator().next().getKeyProperty("port");

        map.put("port", port);
        map.put("address", host);

        return map;



    }




}
