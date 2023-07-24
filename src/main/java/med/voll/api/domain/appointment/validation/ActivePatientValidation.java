package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidation implements AppointmentValidation{

    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentData data){
        if(!patientRepository.existsByIdAndActiveTrue(data.patientId())){
            throw new AppointmentValidationException("There is no active Patient with id "+data.patientId());
        }
    }
}
