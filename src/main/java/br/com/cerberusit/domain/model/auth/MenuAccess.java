package br.com.cerberusit.domain.model.auth;

import br.com.cerberusit.controller.request.MenuAccessRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "MenuAccessEntity")
@Table(name = "t_menu_accesses", schema = "sch_auth")
public class MenuAccess extends AbstractEntity{

    @Column(name = "menu_path")
    private String menuPath;
    @Column(name = "menu_title")
    private String menuTitle;

    @OneToMany(mappedBy = "menuAccesses", orphanRemoval = true)
    private Set<ProfileMenuAccess> profileMenuAccesses = new LinkedHashSet<>();

}
