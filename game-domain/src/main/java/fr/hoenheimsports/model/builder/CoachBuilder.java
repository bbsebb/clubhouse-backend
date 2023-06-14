package fr.hoenheimsports.model.builder;

import fr.hoenheimsports.model.Coach;
import fr.hoenheimsports.model.PhoneNumber;

import java.util.UUID;
import java.util.function.Consumer;

public class CoachBuilder {
    private UUID id;
    private String name;
    private PhoneNumber phoneNumber;

    public CoachBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public CoachBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CoachBuilder withPhoneNumber(Consumer<PhoneNumberBuilder> phoneNumberBuilderFunction) {
        PhoneNumberBuilder phoneNumberBuilder = new PhoneNumberBuilder();
        phoneNumberBuilderFunction.accept(phoneNumberBuilder);
        this.phoneNumber = phoneNumberBuilder.build();
        return this;
    }

    public CoachBuilder withPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Coach build() {
        if (id == null) {
            id = UUID.randomUUID();
        }
        return new Coach(id, name, phoneNumber);
    }
}
