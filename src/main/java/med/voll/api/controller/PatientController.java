package med.voll.api.controller;

import med.voll.api.patient.PatientListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.Patient;
import med.voll.api.patient.PatientRegistrationData;
import med.voll.api.patient.PatientRepository;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid PatientRegistrationData data){
        repository.save(new Patient(data));
    }

    @GetMapping
    public List<PatientListData> searchAll(){
        return repository.findAll().stream().map(PatientListData::new).toList();
    }
    
}
