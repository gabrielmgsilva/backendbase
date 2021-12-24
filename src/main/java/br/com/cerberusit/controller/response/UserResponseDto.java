package br.com.cerberusit.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseDto implements Serializable {
    private final Long id;
    private final boolean isActive;
    private final String email;
    private final String login;
    private final ProfileResponseDto profile;
}
