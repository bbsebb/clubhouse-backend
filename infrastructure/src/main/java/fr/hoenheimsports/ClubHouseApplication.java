package fr.hoenheimsports;


import fr.hoenheimsports.repository.game.entity.*;
import fr.hoenheimsports.repository.game.entity.game.*;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

@EnableAsync
@SpringBootApplication
public class ClubHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubHouseApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserCreate userCreate, fr.hoenheimsports.repository.booking.entity.HallEntityRepository hallBookEntityRepository, RoleCreate roleCreate, UserUpdate userUpdate
            , HallEntityRepository hallEntityRepository,
                            CoachEntityRepository coachEntityRepository,
                            RefereeEntityRepository refereeEntityRepository,
                            CompetitionEntityRepository competitionEntityRepository,
                            PoolEntityRepository poolEntityRepository){
        return args -> {
/*            // User init
            Role roleUser = roleCreate.create("USER");
            Role roleAdmin = roleCreate.create("ADMIN");
            User user = userCreate.create("admin",new BCryptPasswordEncoder().encode("1234"),"seb@seb.seb");
            userUpdate.addRole(user.getId(),roleUser.getId(),roleAdmin.getId());

            // unknown hall game  init
            HallEntity hallEntity = new HallEntity();
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setStreet("unknown");
            addressEntity.setCity("unknown");
            addressEntity.setPostalCode(0);
            hallEntity.setGlueAuthorization(GlueAuthorizationEntity.UNKNOWN);
            hallEntity.setAddress(addressEntity);
            hallEntity.setName("unknown");
            hallEntity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            hallEntityRepository.save(hallEntity);


            // unknown coach init
            CoachEntity coachEntity = new CoachEntity();
            coachEntity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            coachEntity.setName("unknown");
            coachEntity.setPhoneNumber(new PhoneNumberEntity("unknown"));
            coachEntityRepository.save(coachEntity);

            //unknown referee init
            RefereeEntity refereeEntity = new RefereeEntity();
            refereeEntity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            refereeEntity.setName("unknown");
            refereeEntityRepository.save(refereeEntity);

            //unknown competition init
            CompetitionEntity competitionEntity = new CompetitionEntity();
            competitionEntity.setName("unknown");
            competitionEntity = competitionEntityRepository.save(competitionEntity);

            //unknown pool init
            PoolEntity poolEntity = new PoolEntity();
            poolEntity.setCode("unknown");
            poolEntity.setName("unknown");
            poolEntity.setCompetition(competitionEntity);
            poolEntityRepository.save(poolEntity);

            // hall book  init
            fr.hoenheimsports.repository.booking.entity.booking.HallEntity hall = new fr.hoenheimsports.repository.booking.entity.booking.HallEntity();
            hall.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
            hall.setCapacity(120);
            hall.setName("Club house");
            fr.hoenheimsports.repository.booking.entity.booking.AddressEntity address = new fr.hoenheimsports.repository.booking.entity.booking.AddressEntity();
            address.setStreet("rue des Vosges");
            address.setCp(67800);
            address.setCity("HOENHEIM");
            hall.setAddress(address);
            hallBookEntityRepository.save(hall);*/
        };
    }

}