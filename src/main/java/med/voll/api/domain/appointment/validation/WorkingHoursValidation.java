package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkingHoursValidation implements AppointmentValidation{
    private int openingHour = 7;
    private int lastValidAppointment = 18;

    public void validate(AppointmentData data) {
        if (data.dataTime().getDayOfWeek().equals(DayOfWeek.SUNDAY) || data.dataTime().getHour() < openingHour || data.dataTime().getHour() > lastValidAppointment) {
            throw new AppointmentValidationException("Appointment outside clinic opening hours");
        }
    }
}
