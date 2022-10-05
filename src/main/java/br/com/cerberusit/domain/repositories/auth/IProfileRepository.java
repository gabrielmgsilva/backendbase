package br.com.cerberusit.domain.repositories.auth;

import br.com.cerberusit.domain.model.auth.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProfileRepository extends JpaRepository<Profile, Long> {
}
