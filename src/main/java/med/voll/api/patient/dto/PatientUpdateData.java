package med.voll.api.patient.dto;

import med.voll.api.address.AddressData;

public record PatientUpdateData(
        Long id,
        String name,
        String phone,
        AddressData address) {
}
