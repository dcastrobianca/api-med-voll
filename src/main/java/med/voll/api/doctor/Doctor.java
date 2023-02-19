package med.voll.api.doctor;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;

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

    public Doctor(DoctorRegistrationData data) {
        this.name = data.name();
        this.email = data.email();
        this.crm = data.crm();
        this.speciality = data.speciality();
        this.address = new Address(data.address());
        this.phone = data.phone();
    }
}
