package br.com.cerberusit.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ProfileMenuAccessResponseDto implements Serializable {
    private Long id;
    private boolean read;
    private boolean write;
    private MenuAccessResponseDto menuAccesses;
}
