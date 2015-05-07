package jgluna.potlach.main;

import jgluna.potlach.repository.RepositoryInterface;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackageClasses = RepositoryInterface.class)
@Configuration
@EnableWebMvc
@ComponentScan
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
