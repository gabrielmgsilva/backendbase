package br.com.cerberusit.domain.repositories.auth;

import br.com.cerberusit.domain.model.auth.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByDeletedFalse(Pageable pageable);
    @Query("select u from UserEntity u where u.email = ?1 or u.login = ?1 and u.deleted = false")
    Optional<User> findByEmailOrLoginAndDeletedFalse(String username);
    @Modifying
    @Query("update UserEntity AS u set u.deleted = true where u.id = :id")
    void deleteUserById(@Param("id") Long id);
}