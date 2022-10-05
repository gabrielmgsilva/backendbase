package br.com.cerberusit.domain.model.auth;

import br.com.cerberusit.controller.request.ProfileMenuAccessRequestDto;
import br.com.cerberusit.controller.request.ProfileRequestDto;
import br.com.cerberusit.service.utils.Mapper;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
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

    public Profile(ProfileRequestDto profileRequestDto){
        this.setId(null != profileRequestDto.getId() ? profileRequestDto.getId() : null);
        this.description = profileRequestDto.getDescription();
        this.setActive(profileRequestDto.isActive());
    }

    @Override
    public String toString() {
        return "Profile{" +
                "description='" + description + '\'' +
                ", users=" + users +
                ", profileMenuAccesses=" + profileMenuAccesses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(description, profile.description) && Objects.equals(users, profile.users) && Objects.equals(profileMenuAccesses, profile.profileMenuAccesses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, users, profileMenuAccesses);
    }
}
