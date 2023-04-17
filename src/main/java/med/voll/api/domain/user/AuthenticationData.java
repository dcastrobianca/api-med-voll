package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationData(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
