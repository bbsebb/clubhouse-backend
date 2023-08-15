package fr.hoenheimsports;


import fr.hoenheimsports.repository.user.entity.UserEntity;
import fr.hoenheimsports.repository.user.entity.UserEntityRepository;
import fr.hoenheimsports.userdomain.UserCreateImpl;
import fr.hoenheimsports.userdomain.api.RoleCreate;
import fr.hoenheimsports.userdomain.api.UserCreate;
import fr.hoenheimsports.userdomain.api.UserUpdate;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.model.User;
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
    CommandLineRunner start(UserCreate userCreate, RoleCreate roleCreate, UserUpdate userUpdate){
        return args -> {

            Role roleUser = roleCreate.create("USER");
            Role roleAdmin = roleCreate.create("ADMIN");

            //User user1 = userCreate.create("user1","1234","sebastien.burckhardt@gmail.com");
            //User user2 = userCreate.create("admin","1234","sebastien.burckhardt@hoenheimsports.fr");

            //userUpdate.addRole(user1.getId(),roleUser.getId());
            //userUpdate.addRole(user2.getId(),roleUser.getId(),roleAdmin.getId());

        };
    }

}