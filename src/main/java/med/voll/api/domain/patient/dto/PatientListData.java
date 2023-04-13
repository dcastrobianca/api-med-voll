package med.voll.api.domain.patient.dto;

import med.voll.api.domain.patient.Patient;

public record PatientListData (Long id, String name, String email, String CPF){
    public PatientListData(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
