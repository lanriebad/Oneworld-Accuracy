package com.test.assessment.repository;

import com.test.assessment.model.Role;
import com.test.assessment.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Serializable> {

    Optional<Role> findRoleByName(RoleName role);

}
