package com.test.assessment.service;


import com.test.assessment.dao.UserInformationDAO;
import com.test.assessment.dto.UserRequest;
import com.test.assessment.model.Role;
import com.test.assessment.model.RoleName;
import com.test.assessment.model.User;
import com.test.assessment.repository.RoleRepository;
import com.test.assessment.repository.UserInformationRepository;
import com.test.assessment.utils.DataTableOutputPagination;
import com.test.assessment.utils.DefaultServiceResponse;
import com.test.assessment.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("userInformationService")
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private UserInformationDAO userInformationDAO;

    @Autowired
    private UserInformationRepository userInformationRepository;


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public DefaultServiceResponse getAllUserRecords(Pageable pageable) {
        Page<User> page = userInformationRepository.findAll(pageable);
        DefaultServiceResponse<DataTableOutputPagination<User>> tableValue = new DefaultServiceResponse<>();
        if (!page.isEmpty() && !page.getContent().isEmpty()) {
            DataTableOutputPagination<User> table = new DataTableOutputPagination<>();
            table.setData(page.getContent());
            table.setRecordsFiltered(page.getTotalElements());
            table.setRecordsTotal(page.getTotalElements());
            tableValue.setResponseCode(ServiceResponse.ResponseCode.SUCCESS.getCode());
            tableValue.setResponseMsg(ServiceResponse.ResponseCode.SUCCESS.getDefaultMessage());
            tableValue.setResponseData(Collections.singletonList(table));
        } else {
            tableValue.setResponseCode(ServiceResponse.ResponseCode.NO_RECORD_FOUND.getCode());
            tableValue.setResponseMsg(ServiceResponse.ResponseCode.NO_RECORD_FOUND.getDefaultMessage());
        }
        return tableValue;
    }

    @Override
    public Optional<User> findIfUserExistsById(Long id) {
        return userInformationRepository.findById(id);
    }

    @Override
    public void deactivateUserById(Long id) {
        userInformationDAO.deactivateUserById(id);
    }

    @Override
    public void updateUserById(User userInfo, UserRequest request) {
        User updateUser = userInfo;
        updateUser.setTitle(request.getTitle());
        updateUser.setFirstname(request.getFirstname());
        updateUser.setPassword(request.getPassword());
        updateUser.setLastname(request.getLastname());
        updateUser.setMobile(request.getMobile());
        userInformationRepository.save(updateUser);
    }

    @Override
    public User findUserById(Long id) {
        return userInformationRepository.findBUserById(id);
    }

    @Override
    public Optional<User> findIfUserExistsByEmail(String email) {
        return userInformationRepository.findByEmail(email);
    }

    @Override
    public void createUser(UserRequest request) {
        User user = new User();
        user.setDateregistered(new Date());
        user.setEmail(request.getEmail());
        user.setTitle(request.getTitle());
        user.setFirstname(request.getFirstname());
        user.setPassword(request.getPassword());
        user.setLastname(request.getLastname());
        user.setMobile(request.getMobile());
        user.setVerified("N");
        Set<String> strRoles = request.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findRoleByName(RoleName.ROLE_USER)
                    .orElseThrow(() -> new
                            RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                System.out.println(role);
                if ("ADMIN".equalsIgnoreCase(role.toUpperCase())) {
                    Role adminRole = roleRepository.findRoleByName(RoleName.ROLE_ADMIN).
                    orElseThrow(() -> new
                                    RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findRoleByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new
                                    RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userInformationRepository.save(user);
    }

    @Override
    public void verifyUserByEmail(String email) {
        userInformationDAO.verifyUserByEmail(email);

    }


}
