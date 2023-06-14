package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Address;
import fr.hoenheimsports.model.GlueAuthorization;
import fr.hoenheimsports.model.Halle;

import java.util.UUID;
import java.util.function.Consumer;

public class HalleBuilder {
    private UUID id;
    private String name;
    private Address address;
    private GlueAuthorization glueAuthorization;

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
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Halle(id, name, address, glueAuthorization);
    }
}
