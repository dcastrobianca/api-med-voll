package med.voll.api.domain.appointment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.appointment.Appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsData(Long id, Long doctorId, Long patientID, LocalDateTime dataTime) {
    public AppointmentDetailsData(Appointment appointment){
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDateTime());
    }
}
