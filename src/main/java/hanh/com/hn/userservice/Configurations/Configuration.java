package hanh.com.hn.userservice.Configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

    @Component
    @org.springframework.context.annotation.Configuration
    @PropertySource("classpath:application.properties")
    @ConfigurationProperties(prefix = "server")
    public class Configuration {
        private int port;

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }

