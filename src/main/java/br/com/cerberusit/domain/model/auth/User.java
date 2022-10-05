package br.com.cerberusit.domain.model.auth;

import br.com.cerberusit.controller.request.UserRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "t_users", schema = "sch_auth")
public class User extends AbstractEntity{

    @Column(name = "name", length = 100)
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "password_expired")
    private boolean passwordExpired;
    @Column(name = "account_locked")
    private boolean accountLocked;
    @Column(name = "deleted")
    private boolean deleted;

    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public User(UserRequestDto userRequestDto) {
        this.setId(null != userRequestDto.getId() ? userRequestDto.getId() : null);
        this.name = userRequestDto.getName();
        this.login = userRequestDto.getLogin();
        this.email = userRequestDto.getEmail();
        this.address = new Address(userRequestDto.getAddress());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return passwordExpired == user.passwordExpired
                && accountLocked == user.accountLocked
                && Objects.equals(name, user.name)
                && Objects.equals(email, user.email)
                && Objects.equals(login, user.login)
                && Objects.equals(password, user.password)
                && Objects.equals(address, user.address)
                && Objects.equals(profile, user.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, login, password, passwordExpired, accountLocked, address, profile);
    }
}
