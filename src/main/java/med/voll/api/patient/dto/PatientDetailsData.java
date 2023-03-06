package med.voll.api.patient.dto;

import med.voll.api.address.Address;
import med.voll.api.patient.Patient;

public record PatientDetailsData(Long id, String name, String email, String phone,String cpf, Address addresss) {

    public PatientDetailsData(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(),patient.getCpf(), patient.getAddress());
    }
}
