package br.com.cerberusit.domain.repositories.auth;

import br.com.cerberusit.domain.model.auth.ProfileMenuAccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileMenuAccessRepository extends JpaRepository<ProfileMenuAccess, Long> {
}
