package med.voll.api.doctor.dto;

import med.voll.api.address.AddressData;

public record DoctorUpdateData(Long id, String name, String phone, AddressData address) {
    
}
