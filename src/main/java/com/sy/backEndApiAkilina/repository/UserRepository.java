package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Role;
import com.sy.backEndApiAkilina.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrNumero(String email, String numero);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByNumero (String numero);


    User findByRoles(Role role);

}
