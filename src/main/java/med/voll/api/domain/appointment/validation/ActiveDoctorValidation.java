package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidation implements AppointmentValidation{
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentData data){
        if(data.doctorId()!=null && !doctorRepository.existsByIdAndActiveTrue(data.doctorId())){
            throw new AppointmentValidationException("There is no active Doctor with id "+data.doctorId());
        }
    }
}
