package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRegistrationData;
import med.voll.api.doctor.DoctorRepository;

@RestController
@RequestMapping ("/doctors")
public class DoctorController {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DoctorRegistrationData data){
        doctorRepository.save(new Doctor(data));
    }
    
}
