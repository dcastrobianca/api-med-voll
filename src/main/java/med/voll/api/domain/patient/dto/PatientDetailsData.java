package med.voll.api.domain.patient.dto;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.Patient;

public record PatientDetailsData(Long id, String name, String email, String phone,String cpf, Address addresss) {

    public PatientDetailsData(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(),patient.getCpf(), patient.getAddress());
    }
}
