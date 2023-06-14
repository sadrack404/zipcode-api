package br.com.hub.correio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CorreioApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(CorreioApplication.class, args);
    }

    public static void close(int code) {
        SpringApplication.exit(context, () -> code);
    }

}
