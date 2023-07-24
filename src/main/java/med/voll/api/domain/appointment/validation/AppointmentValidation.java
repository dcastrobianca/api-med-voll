package med.voll.api.domain.appointment.validation;

import med.voll.api.domain.appointment.dto.AppointmentData;

public interface AppointmentValidation {
    void validate(AppointmentData data);
}
