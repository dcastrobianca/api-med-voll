package med.voll.api.domain.appointment;

import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.domain.appointment.dto.AppointmentDetailsData;
import med.voll.api.domain.appointment.validation.AppointmentValidation;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exception.AppointmentValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleAppointments {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentValidation> validations;

    public AppointmentDetailsData schedule(AppointmentData data){
        validations.forEach(v -> v.validate(data));
        Patient patient = patientRepository.getReferenceById(data.patientId());
        Doctor doctor = chooseDoctor(data);
        Appointment appointment = new Appointment(null, doctor, patient,data.dataTime());
        appointmentRepository.save(appointment);

        return new AppointmentDetailsData(appointment);
    }

    private Doctor chooseDoctor(AppointmentData data) {
        if(data.doctorId()!=null){
            return doctorRepository.getReferenceById(data.doctorId());
        }
        if(data.speciality()==null){
            throw new AppointmentValidationException("Speciality must be informed when doctor is not previously chosen");
        }

        Doctor doctor = doctorRepository.getRandomDoctorFromSpecialityOnDate(data.speciality(), data.dataTime());
        if(doctor== null){
            throw new AppointmentValidationException("There is no doctor available at this time");
        }
        return doctor;
    }
}
