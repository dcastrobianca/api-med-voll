package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.ScheduleAppointments;
import med.voll.api.domain.appointment.dto.AppointmentData;
import med.voll.api.domain.appointment.dto.AppointmentDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping ("/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {
    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private ScheduleAppointments scheduler;
    
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid AppointmentData data, UriComponentsBuilder uri) {
        AppointmentDetailsData appointmentDetailsData= scheduler.schedule(data);
        return ResponseEntity.ok(appointmentDetailsData);
    }
}
