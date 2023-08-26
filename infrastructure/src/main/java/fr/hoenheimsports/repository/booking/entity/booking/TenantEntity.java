package fr.hoenheimsports.repository.booking.entity.booking;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class TenantEntity  extends HallUserEntity {

    @Embedded
    private AddressEntity address;
    private boolean isMember;

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TenantEntity that)) return false;
        if (!super.equals(o)) return false;
        return isMember() == that.isMember() && Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAddress(), isMember());
    }

    @Override
    public String toString() {
        return super.toString() + " - TenantEntity{" +
                "address=" + address +
                ", isMembre=" + isMember +
                '}';
    }
}
