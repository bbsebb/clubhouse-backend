package fr.hoenheimsports.bookdomain.model;

import java.util.Objects;
import java.util.UUID;

public final class Tenant extends HallUser{

    private Address address;
    private boolean isMember;
    public Tenant(UUID id, String username, String email, Address address, boolean isMember) {
        super(id,username,email);
        Objects.requireNonNull(address, "address should be not null");
        this.address = address;
        this.isMember = isMember;
    }
    public Address getAddress() {
        return address;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setMember(boolean member) {
        isMember = member;
    }
}
