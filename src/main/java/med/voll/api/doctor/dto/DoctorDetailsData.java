package med.voll.api.doctor.dto;

import med.voll.api.address.Address;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.Speciality;

public record DoctorDetailsData(Long id, String name, String crm, String email, String phone, Speciality speciality, Address address) {

    public DoctorDetailsData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getCrm(), doctor.getEmail(), doctor.getPhone(), doctor.getSpeciality(), doctor.getAddress());
    }
}
