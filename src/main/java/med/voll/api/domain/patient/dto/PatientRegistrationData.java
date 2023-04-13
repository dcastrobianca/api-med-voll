package med.voll.api.domain.patient.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressData;

public record PatientRegistrationData(
    @NotBlank
    String name,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String phone, 
    @NotBlank
    @Pattern(regexp = "\\d{11}")
    String cpf,
    AddressData address) {
}
