package br.com.systemit.auth.domain.repository;

import br.com.systemit.auth.domain.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
