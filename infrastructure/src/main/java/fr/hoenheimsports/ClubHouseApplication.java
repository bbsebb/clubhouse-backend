package fr.hoenheimsports;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"fr.hoenheimsports.gamedomain", "fr.hoenheimsports"})
public class ClubHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClubHouseApplication.class, args);
    }

}