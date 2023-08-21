package med.voll.api.domain.doctor;

import med.voll.api.domain.doctor.dto.DoctorDetailsData;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository repository;

    public DoctorDetailsData create(DoctorRegistrationData data){
        Doctor doctor = new Doctor(data);
        repository.save(doctor);
        return new DoctorDetailsData(doctor);
    }
}
