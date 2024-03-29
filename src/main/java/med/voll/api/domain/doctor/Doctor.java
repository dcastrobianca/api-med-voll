package med.voll.api.domain.doctor;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.doctor.dto.DoctorRegistrationData;
import med.voll.api.domain.doctor.dto.DoctorUpdateData;

@Entity (name = "Doctor")
@Table (name = "doctors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode (of = "id")
public class Doctor {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column (name = "doctor_name")
    private String name;
    private String email;
    private String crm;
    private String phone;

    @Enumerated (EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;
    private boolean active;

    public Doctor(DoctorRegistrationData data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.speciality = data.speciality();
        this.address = new Address(data.address());
        this.phone = data.phone();
        this.active = true;
    }

    public void updateData(@Valid DoctorUpdateData data) {
        if(data.name()!=null && !data.name().isBlank()){
            this.name = data.name();
        }

        if(data.phone()!=null && !data.phone().isBlank()){
            this.phone = data.phone();
        }

        if(data.address()!=null){
            this.address = new Address(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
