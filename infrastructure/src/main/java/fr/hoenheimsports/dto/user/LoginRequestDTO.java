package fr.hoenheimsports.dto.user;

public record LoginRequestDTO(
        String login,
        String password
) {}