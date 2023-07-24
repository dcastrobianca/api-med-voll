package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class EarlySchedulingValidation implements AppointmentValidation{

    private int minimumAdvanceMinutes = 30;

    public void validate(AppointmentData data){
        LocalDateTime now = LocalDateTime.now();
        if(Duration.between(now, data.dataTime()).toMinutes()< minimumAdvanceMinutes){
            throw new AppointmentValidationException("At least 30 minutes' notice is required");
        }
    }
}
