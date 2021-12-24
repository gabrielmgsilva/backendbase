package br.com.cerberusit.domain.model.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "active")
    private boolean isActive;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist(){
        this.setCreatedDate(LocalDateTime.now());
        this.setActive(Boolean.TRUE);
    }

    @PreUpdate
    public void preUpdate(){
        this.setUpdatedDate(LocalDateTime.now());
    }
}
