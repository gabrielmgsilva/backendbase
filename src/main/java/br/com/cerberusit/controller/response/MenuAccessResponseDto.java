package br.com.cerberusit.controller.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class MenuAccessResponseDto implements Serializable {
    private final Long id;
    private final String menuPath;
    private final String menuTitle;
    private final String menuIcon;
}
