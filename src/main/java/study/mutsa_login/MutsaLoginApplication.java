package study.mutsa_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class MutsaLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(MutsaLoginApplication.class, args);
    }

}
