package med.voll.api.domain.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;
import med.voll.api.domain.patient.dto.PatientRegistrationData;
import med.voll.api.domain.patient.dto.PatientUpdateData;

@Entity
@Table (name = "patients")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    @Embedded
    private Address address;

    private boolean active;


    public Patient(PatientRegistrationData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
        this.active = true;
    }


    public void update(PatientUpdateData data) {
        if(data.name()!=null){
            this.name = data.name();
        }
        if(data.address()!=null){
            this.address = new Address(data.address());
        }
        if(data.phone()!= null){
            this.phone = data.phone();
        }
    }

    public void delete() {
        this.active=false;
    }
}
