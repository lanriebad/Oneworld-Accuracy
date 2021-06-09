package com.test.assessment.configuration;

import com.test.assessment.model.Role;
import com.test.assessment.model.RoleName;
import com.test.assessment.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class Seed {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void Seeder(){
        Optional<Role> adminRole = roleRepository.findRoleByName(RoleName.ROLE_ADMIN);
        Optional<Role>  userRole = roleRepository.findRoleByName(RoleName.ROLE_USER);
        if(!adminRole.isPresent()){
            createRole(RoleName.ROLE_ADMIN);
        }
        if(!userRole.isPresent()){
            createRole(RoleName.ROLE_USER);
        }
    }

    public Role createRole(RoleName role){
        return roleRepository.save(Role.builder().name(role).build());

    }
}
