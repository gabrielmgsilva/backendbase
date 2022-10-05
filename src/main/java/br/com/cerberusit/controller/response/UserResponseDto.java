package br.com.cerberusit.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserResponseDto implements Serializable {
    private Long id;
    private boolean isActive;
    private String email;
    private String login;
    private ProfileResponseDto profile;
}
