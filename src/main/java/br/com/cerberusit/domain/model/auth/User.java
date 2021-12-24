package br.com.cerberusit.domain.model.auth;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
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
    @Column(name = "account_locked", unique = true, nullable = false)
    private boolean accountLocked;
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
