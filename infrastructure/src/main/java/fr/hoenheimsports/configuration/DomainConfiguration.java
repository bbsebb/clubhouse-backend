package fr.hoenheimsports.configuration;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.spi.stub.CSVToGamesStub;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryInMemory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "fr.hoenheimsports.gamedomain",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class, Stub.class})},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {CSVToGamesStub.class, GameRepositoryInMemory.class})})
public class DomainConfiguration {
}

