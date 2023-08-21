package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByPatientIdAndAndDateTimeBetween(Long idPatient, LocalDateTime firstValidAppointment, LocalDateTime lastValidAppointment);

    boolean existsByDoctorIdAndAndDateTime(Long idDoctor, LocalDateTime dateTime);
}
