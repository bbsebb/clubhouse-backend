package fr.hoenheimsports.dto.booking;

public record HallUserCreateDTO(String id, String username, String email, AddressDTO address) {
    public boolean isRegistered() {
        return id != null  && !id.isEmpty();
    }
}
