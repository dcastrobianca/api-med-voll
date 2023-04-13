package med.voll.api.domain.patient.dto;

import med.voll.api.domain.address.AddressData;

public record PatientUpdateData(
        Long id,
        String name,
        String phone,
        AddressData address) {
}
