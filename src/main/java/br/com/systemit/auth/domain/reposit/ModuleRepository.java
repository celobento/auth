package br.com.systemit.auth.domain.reposit;

import br.com.systemit.auth.domain.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
