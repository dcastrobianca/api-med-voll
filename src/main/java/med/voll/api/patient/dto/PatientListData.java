package med.voll.api.patient.dto;

import med.voll.api.patient.Patient;

public record PatientListData (Long id, String name, String email, String CPF){
    public PatientListData(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
