package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneAppointmentPerPatientPerDayValidation implements AppointmentValidation{
    private int openingHour = 7;
    private int lastValidAppointment = 18;
    @Autowired
    public AppointmentRepository appointmentRepository;

    public void validate(AppointmentData data){
        if(appointmentRepository.existsByPatientIdAndAndDateTimeBetween(data.patientId(), data.dataTime().withHour(openingHour), data.dataTime().withHour(lastValidAppointment))){
            throw new AppointmentValidationException("There is already an Appointment for this patient on date "+data.dataTime().toLocalDate());
        }
    }
}
