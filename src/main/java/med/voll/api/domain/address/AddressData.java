package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
    @NotBlank
    String street,
    @NotBlank
    String district,
    @NotBlank
    @Pattern (regexp = "\\d{8}")
    String postcode,
    @NotBlank
    String city,
    @NotBlank
    String state, 
    String complement, 
    String number
    ) {
    
}
