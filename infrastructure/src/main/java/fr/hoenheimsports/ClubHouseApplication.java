package fr.hoenheimsports;


import fr.hoenheimsports.repository.booking.entity.HallEntityRepository;
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
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication(scanBasePackages = {"fr.hoenheimsports.gamedomain", "fr.hoenheimsports.bookdomain", "fr.hoenheimsports.userdomain","fr.hoenheimsports"})
public class ClubHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubHouseApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserCreate userCreate, RoleCreate roleCreate, UserUpdate userUpdate, HallEntityRepository hallEntityRepository){
        return args -> {
/*
            Role roleUser = roleCreate.create("USER");
            Role roleAdmin = roleCreate.create("ADMIN");
            User user = userCreate.create("user1",new BCryptPasswordEncoder().encode("1234"),"seb@seb.seb");

            HallEntity hallEntity = new HallEntity();
            hallEntity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            hallEntity.setName("Club house");
            hallEntity.setCapacity(100);
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setCity("Hoenheim");
            addressEntity.setCp(67800);
            addressEntity.setStreet("rue des Vosges");
            hallEntity.setAddress(addressEntity);
            hallEntityRepository.save(hallEntity);*/
            //User user1 = userCreate.create("user1","1234","sebastien.burckhardt@gmail.com");
            //User user2 = userCreate.create("admin","1234","sebastien.burckhardt@hoenheimsports.fr");

            //userUpdate.addRole(user1.getId(),roleUser.getId());
            //userUpdate.addRole(user2.getId(),roleUser.getId(),roleAdmin.getId());

        };
    }

}