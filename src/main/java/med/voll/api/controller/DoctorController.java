package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.dto.DoctorListData;
import med.voll.api.doctor.dto.DoctorRegistrationData;
import med.voll.api.doctor.dto.DoctorUpdateData;

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

    @GetMapping
    public Page<DoctorListData> search(@PageableDefault(size = 10, sort = {"name"}) Pageable page){
        return doctorRepository.findAllByActiveTrue(page).map(DoctorListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateData data){
        Doctor doctor = doctorRepository.getReferenceById(data.id());
        doctor.updateData(data);
    }
    

    @DeleteMapping ("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}
