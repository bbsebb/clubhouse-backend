package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.HallRepository;

import java.util.UUID;
import java.util.function.Consumer;

public class HalleBuilder {
    public static HalleBuilder builder() {
        return new HalleBuilder();
    }
    private UUID id;
    private String name;
    private Address address;
    private GlueAuthorization glueAuthorization;
    private HallRepository halleRepository;
    public HalleBuilder addIdGeneratorFromRepository(HallRepository coachRepository) {
        this.halleRepository = coachRepository;
        return this;
    }

    public HalleBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public HalleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public HalleBuilder withAddress(Consumer<AddressBuilder> addressBuilderFunction) {
        AddressBuilder addressBuilder = new AddressBuilder();
        addressBuilderFunction.accept(addressBuilder);
        this.address = addressBuilder.build();
        return this;
    }

    public HalleBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public HalleBuilder withGlueAuthorization(GlueAuthorization glueAuthorization) {
        this.glueAuthorization = glueAuthorization;
        return this;
    }

    public Hall build() {
        if(this.halleRepository != null) {
            var optionalHall = this.halleRepository.findHallByKeys(name,address.street(),address.postalCode(),address.city());
            optionalHall.ifPresent(halle -> this.id = halle.id());
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Hall(id, name, address, glueAuthorization);
    }
}
