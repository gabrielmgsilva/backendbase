package br.com.cerberusit.domain.model.auth;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProfileMenuAccessEntity")
@Table(name = "t_profile_menu_acesses", schema = "sch_auth")
public class ProfileMenuAccess{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "canRead")
    private boolean read;
    @Column(name = "canWrite")
    private boolean write;


    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profiles;

    @ManyToOne
    @JoinColumn(name = "menu_accesses_id")
    private MenuAccess menuAccesses;

}
