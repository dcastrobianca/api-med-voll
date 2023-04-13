package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.address.AddressData;

public record DoctorUpdateData(Long id, String name, String phone, AddressData address) {
    
}
