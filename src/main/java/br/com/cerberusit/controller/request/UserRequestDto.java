package br.com.cerberusit.controller.request;

import lombok.Data;

@Data
public class UserRequestDto {

    private Long id;
    private String name;
    private String email;
    private String login;
    private String password;
    private Long profileId;
    private AddressRequestDto address;

}
