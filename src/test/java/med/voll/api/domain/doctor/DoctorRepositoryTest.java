package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("There is no doctor available on this data")
    void shouldNotFindFreeDoctor() {
        //given
        Speciality speciality = Speciality.CARDIOLOGY;
        LocalDateTime appointmentDatatime = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,00);
        Doctor doctor = createDoctor("Fulano", "fulano@voll.med", "123456789", "123456", speciality);
        Patient patient = createPatient("Siclano", "siclano@email.com", "12345678", "1112223344");
        createAppointment(doctor,patient, appointmentDatatime);
        //when
        Doctor randomDoctor = doctorRepository.getRandomDoctorFromSpecialityOnDate(speciality, appointmentDatatime);
        //then
        assertThat(randomDoctor).isNull();
    }

    @Test
    @DisplayName("There is doctor available on this data")
    void shouldFindDoctor() {
        //given
        Speciality speciality = Speciality.CARDIOLOGY;
        LocalDateTime appointmentDatatime = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,00);
        Doctor doctor = createDoctor("Fulano", "fulano@voll.med", "123456789", "123456", speciality);

        //when
        Doctor randomDoctor = doctorRepository.getRandomDoctorFromSpecialityOnDate(speciality, appointmentDatatime);
        //then
        assertThat(randomDoctor).isEqualTo(doctor);
    }

    private Doctor createDoctor(String name, String email, String phone, String crm, Speciality speciality){
        Doctor doctor = new Doctor(new DoctorRegistrationData(name, email, phone, crm, speciality, createAddress()));
        em.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, String phone, String cpf){
        Patient patient = new Patient(new PatientRegistrationData(name,email, phone,cpf, createAddress()));
        em.persist(patient);
        return  patient;
    }

    private Appointment createAppointment(Doctor doctor, Patient patient, LocalDateTime dateTime){
        Appointment appointment= new Appointment(null, doctor, patient, dateTime);
        em.persist(appointment);
        return appointment;
    }

    private AddressData createAddress() {
        return new AddressData("Street a", "District b", "77600000", "Paradise", "TO", null, "123");
    }

}