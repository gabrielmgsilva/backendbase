package br.com.cerberusit.controller.response;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class ProfileResponseDto implements Serializable {
    private final Long id;
    private final boolean isActive;
    private final String description;
    private final Set<ProfileMenuAccessResponseDto> profileMenuAccesses;
}
