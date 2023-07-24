package med.voll.api.domain.appointment.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Speciality;

import java.time.LocalDateTime;

public record AppointmentData(
        Long doctorId,
        @NotNull @JsonAlias("patient_id")
        Long patientId,
        @NotNull @Future
        LocalDateTime dataTime,
        Speciality speciality) {
}
