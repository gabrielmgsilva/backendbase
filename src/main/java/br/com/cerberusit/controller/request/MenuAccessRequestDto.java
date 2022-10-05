package br.com.cerberusit.controller.request;

import lombok.Data;

import javax.persistence.Column;

@Data
public class MenuAccessRequestDto {

    private Long id;
    private boolean canRead;
    private boolean canWrite;
}
