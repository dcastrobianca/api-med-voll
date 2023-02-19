package med.voll.api.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    @Column(name = "address_number")
    private String number;
    private String district;
    private String postcode;
    private String city;
    @Column(name = "address_state")
    private String state;
    private String complement;

    public Address(AddressData data) {
        this.street = data.street();
        this.number = data.number();
        this.district = data.district();
        this.postcode = data.postcode();
        this.city = data.city();
        this.state = data.state();
        this.complement = data.complement();
    }
}
