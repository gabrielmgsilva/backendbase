package br.com.cerberusit.controller.request;

import lombok.Data;

@Data
public class AddressRequestDto {

    private String street;
    private String zipCode;
    private String district;
    private String city;
    private String state;
}
