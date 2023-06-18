package fr.hoenheimsports.gamedomain.builder;

import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Halle;
import fr.hoenheimsports.gamedomain.spi.HalleRepository;

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
    private HalleRepository halleRepository;
    public HalleBuilder addIdGeneratorFromRepository(HalleRepository coachRepository) {
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

    public Halle build() {
        if(this.halleRepository != null) {
            var optionalHall = this.halleRepository.findHallByKeys(name,address.street(),address.postalCode(),address.city());
            if (optionalHall.isPresent()) {
                this.id = optionalHall.get().id();
            }
        }
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Halle(id, name, address, glueAuthorization);
    }
}
