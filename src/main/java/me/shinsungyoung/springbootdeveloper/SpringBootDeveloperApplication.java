package me.shinsungyoung.springbootdeveloper;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.run;

@EnableJpaAuditing // created_at, updated_at 자동업데이트
@SpringBootApplication
public class SpringBootDeveloperApplication {
    public static void main(String[] args){
        run(SpringBootDeveloperApplication.class, args);
    }
}
