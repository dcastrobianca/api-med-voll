package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.doctor.DoctorService;
import med.voll.api.domain.doctor.dto.DoctorDetailsData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.dto.DoctorListData;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import med.voll.api.domain.doctor.dto.DoctorUpdateData;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping ("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailsData> create(@RequestBody @Valid DoctorRegistrationData data, UriComponentsBuilder uriBuilder){
        DoctorDetailsData doctor =service.create(data);
        return ResponseEntity.created(uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.id()).toUri()).body(doctor);
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> search(@PageableDefault(size = 10, sort = {"name"}) Pageable page){
        return ResponseEntity.ok(doctorRepository.findAllByActiveTrue(page).map(DoctorListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailsData> update(@RequestBody @Valid DoctorUpdateData data){
        Doctor doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateData(data);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }
    

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DoctorDetailsData> detailById(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

}
