package br.com.cerberusit.domain.repositories.auth;

import br.com.cerberusit.domain.model.auth.MenuAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMenuAccessRepository extends JpaRepository<MenuAccess, Long> {
}
