package br.com.cerberusit.controller.request;

import lombok.Data;

@Data
public class ProfileMenuAccessRequestDto {

    private Long id;
    private boolean canRead;
    private boolean canWrite;
    private MenuAccessRequestDto menuAccessRequestDto;
}
