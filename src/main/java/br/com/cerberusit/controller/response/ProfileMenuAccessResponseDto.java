package br.com.cerberusit.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProfileMenuAccessResponseDto implements Serializable {
    private final Long id;
    private final boolean read;
    private final boolean write;
    private final MenuAccessResponseDto menuAccesses;
}
