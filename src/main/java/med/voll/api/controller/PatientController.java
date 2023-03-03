package med.voll.api.controller;

import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    public Page<PatientListData> searchAll(@PageableDefault(sort = {"name"}) Pageable page){
        return repository.findAll(page).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateData data){
        Patient patient = repository.getReferenceById(data.id());
        patient.update(data);
    }
    
}
