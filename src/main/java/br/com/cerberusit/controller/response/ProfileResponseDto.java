package br.com.cerberusit.controller.response;

import br.com.cerberusit.domain.model.auth.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
public class ProfileResponseDto implements Serializable {
    private Long id;
    private boolean isActive;
    private String description;
    private Set<ProfileMenuAccessResponseDto> profileMenuAccesses;
}
