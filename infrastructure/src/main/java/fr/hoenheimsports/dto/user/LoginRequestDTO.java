package fr.hoenheimsports.dto.user;

public record LoginRequestDTO(
        String username,
        String password
) {}