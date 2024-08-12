package br.com.systemit.auth.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.systemit.auth.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
    
}
