package med.voll.api.domain.doctor.dto;

import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.Speciality;

public record DoctorDetailsData(Long id, String name, String crm, String email, String phone, Speciality speciality, Address address) {

    public DoctorDetailsData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getCrm(), doctor.getEmail(), doctor.getPhone(), doctor.getSpeciality(), doctor.getAddress());
    }
}
