package br.com.cerberusit.domain.model.auth;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProfileEntity")
@Table(name = "t_profiles", schema = "sch_auth")
public class Profile extends AbstractEntity{

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "profile")
    private Set<User> users = new LinkedHashSet<>();

    @OneToMany(mappedBy = "profiles")
    private Set<ProfileMenuAccess> profileMenuAccesses = new LinkedHashSet<>();

}
