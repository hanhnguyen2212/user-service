package hanh.com.hn.userservice.Configurations;


import hanh.com.hn.userservice.Configurations.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomizedSeverPort implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Autowired
    private Configuration x ;
    @Override
    public void customize(ConfigurableServletWebServerFactory server) {
        server.setPort(x.getPort());
    }

}
