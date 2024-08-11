package br.com.systemit.auth.domain.repository;

import br.com.systemit.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
