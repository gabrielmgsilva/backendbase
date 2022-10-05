package br.com.cerberusit.controller.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MenuAccessResponseDto implements Serializable {
    private Long id;
    private String menuPath;
    private String menuTitle;
}
