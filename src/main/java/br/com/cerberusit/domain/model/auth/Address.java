package br.com.cerberusit.domain.model.auth;

import br.com.cerberusit.controller.request.AddressRequestDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Column(name = "street")
    private String street;
    @Column(name = "zip_code", length = 20)
    private String zipCode;
    @Column(name = "district", length = 100)
    private String district;
    @Column(name = "city", length = 50)
    private String city;
    @Column(name = "state", length = 50)
    private String state;

    public Address(AddressRequestDto address) {
        this.street = address.getStreet();
        this.zipCode = address.getZipCode();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.state = address.getState();
    }
}
