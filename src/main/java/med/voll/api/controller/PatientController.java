package med.voll.api.controller;

import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.dto.PatientDetailsData;
import med.voll.api.domain.patient.dto.PatientListData;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import med.voll.api.domain.patient.dto.PatientUpdateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailsData> create(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder){
        Patient patient=new Patient(data);
        repository.save(patient);
        return ResponseEntity.created(uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri()).body(new PatientDetailsData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> searchAll(@PageableDefault(sort = {"name"}) Pageable page){
        return ResponseEntity.ok(repository.findAllByActiveTrue(page).map(PatientListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDetailsData> update(@RequestBody @Valid PatientUpdateData data){
        Patient patient = repository.getReferenceById(data.id());
        patient.update(data);
        return ResponseEntity.ok(new PatientDetailsData(patient));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsData> detail(@PathVariable Long id){
        Patient patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsData(patient));
    }
    
}
