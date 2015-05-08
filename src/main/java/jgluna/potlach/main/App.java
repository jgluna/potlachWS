package jgluna.potlach.main;

import jgluna.potlach.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
@EnableWebMvc
@ComponentScan
@EntityScan(basePackages = "jgluna.potlach.model")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
