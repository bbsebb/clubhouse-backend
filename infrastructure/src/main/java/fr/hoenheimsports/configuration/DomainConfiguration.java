package fr.hoenheimsports.configuration;

import fr.hoenheimsports.bookdomain.spi.stub.BookingStub;
import fr.hoenheimsports.bookdomain.spi.stub.EmailProviderStub;
import fr.hoenheimsports.bookdomain.spi.stub.HallStub;
import fr.hoenheimsports.bookdomain.spi.stub.HallUserStub;
import fr.hoenheimsports.gamedomain.spi.stub.CSVToGamesStub;
import fr.hoenheimsports.gamedomain.spi.stub.GameRepositoryStub;
import fr.hoenheimsports.gamedomain.spi.stub.PoolRepositoryStub;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {
                "fr.hoenheimsports.gamedomain",
                "fr.hoenheimsports.userdomain",
                "fr.hoenheimsports.bookdomain"
        },
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.gamedomain.annotation.DomainService.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.bookdomain.annotation.DomainService.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.userdomain.annotation.DomainService.class})
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.gamedomain.annotation.Stub.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.bookdomain.annotation.Stub.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {fr.hoenheimsports.userdomain.annotation.Stub.class})

        }
)
public class DomainConfiguration {
}

