package br.com.cerberusit.domain.model.auth;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @Column(name = "menu_icon")
    private String menuIcon;

    @OneToMany(mappedBy = "menuAccesses", orphanRemoval = true)
    private Set<ProfileMenuAccess> profileMenuAccesses = new LinkedHashSet<>();

}
