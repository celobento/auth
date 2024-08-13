package br.com.systemit.auth.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.systemit.auth.domain.entity.User;
import br.com.systemit.auth.domain.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer>{

    @Query("""
            select 
                distinct p.role
            from UserProfile up
            join up.profile p
            join up.user u
            where up.status = true
            and u = ?1
            """)
    List<String> findPermissionsByUser(User user);
    
}
