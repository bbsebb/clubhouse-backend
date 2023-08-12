package fr.hoenheimsports;


import fr.hoenheimsports.repository.user.entity.UserEntity;
import fr.hoenheimsports.repository.user.entity.UserEntityRepository;
import fr.hoenheimsports.userdomain.api.UserCreate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = {"fr.hoenheimsports.gamedomain", "fr.hoenheimsports"})
public class ClubHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubHouseApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserEntityRepository userCreate){
        return args -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(UUID.randomUUID());
            userEntity.setEmail("sebastien.burckhardt@gmail.com");
            userEntity.setPassword(new BCryptPasswordEncoder().encode("1234"));
            userEntity.setUsername("user2");
            userCreate.save(userEntity);
        };
    }

}