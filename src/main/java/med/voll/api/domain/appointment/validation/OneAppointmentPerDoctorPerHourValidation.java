package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneAppointmentPerDoctorPerHourValidation implements AppointmentValidation{
    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentData data) {
        if(data.doctorId()!=null && appointmentRepository.existsByDoctorIdAndAndDateTime(data.doctorId(), data.dataTime())){
            throw new AppointmentValidationException("There is already a appointment for this doctor at this time");
        }
    }
}
