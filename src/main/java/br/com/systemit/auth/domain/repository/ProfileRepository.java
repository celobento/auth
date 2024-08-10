package br.com.systemit.auth.domain.repository;

import br.com.systemit.auth.domain.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
