package br.com.cerberusit.domain.repositories.auth;

import br.com.cerberusit.domain.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}