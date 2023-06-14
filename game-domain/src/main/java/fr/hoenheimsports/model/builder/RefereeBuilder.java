package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.PhoneNumber;
import fr.hoenheimsports.model.Referee;

import java.util.UUID;
import java.util.function.Consumer;

public class RefereeBuilder {
    private UUID id;
    private String name;
    private PhoneNumber phoneNumber;

    public RefereeBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public RefereeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RefereeBuilder withPhoneNumber(Consumer<PhoneNumberBuilder> phoneNumberBuilderFunction) {
        PhoneNumberBuilder phoneNumberBuilder = new PhoneNumberBuilder();
        phoneNumberBuilderFunction.accept(phoneNumberBuilder);
        this.phoneNumber = phoneNumberBuilder.build();
        return this;
    }

    public RefereeBuilder withPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Referee build() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Referee(id, name, phoneNumber);
    }
}
