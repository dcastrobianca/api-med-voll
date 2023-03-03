package med.voll.api.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import med.voll.api.address.Address;
import med.voll.api.address.AddressData;

public record PatientUpdateData(
        Long id,
        String name,
        String phone,
        AddressData address) {
}
