package in.shagunakarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = {"in.shagunakarsh"})
public class GitServerApplication {
    public static void main(String []args) {
        SpringApplication.run(GitServerApplication.class, args);
    }
}
