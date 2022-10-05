package br.com.cerberusit.controller.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class ProfileRequestDto {

    private Long id;
    @JsonAlias("active")
    private boolean isActive;
    @NotNull
    @NotEmpty
    private String description;
    private List<MenuAccessRequestDto> menuAccessDtoList;

}
