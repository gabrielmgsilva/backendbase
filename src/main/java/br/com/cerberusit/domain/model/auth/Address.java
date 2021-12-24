package br.com.cerberusit.domain.model.auth;

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

}
