package fr.hoenheimsports.configuration;

import fr.hoenheimsports.gamedomain.spi.stub.CSVToGamesStub;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {
                "fr.hoenheimsports.gamedomain",
                "fr.hoenheimsports.userdomain"
        },
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.gamedomain.annotation.DomainService.class, fr.hoenheimsports.gamedomain.annotation.Stub.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.userdomain.annotation.DomainService.class, fr.hoenheimsports.userdomain.annotation.Stub.class})
        },
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CSVToGamesStub.class, GameRepositoryInMemory.class})})
public class DomainConfiguration {
}

