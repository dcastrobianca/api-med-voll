package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Speciality;

public record DoctorListData(Long id, String name, String email, String crm, Speciality speciality) {

    public DoctorListData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
