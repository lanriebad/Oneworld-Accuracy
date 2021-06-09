package com.test.assessment.service;

import com.test.assessment.dto.UserRequest;
import com.test.assessment.model.User;
import com.test.assessment.utils.DefaultServiceResponse;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserInformationService {

    DefaultServiceResponse getAllUserRecords(Pageable pageable);

    Optional<User> findIfUserExistsById(Long id);

    void deactivateUserById(Long id);

    void updateUserById(User userInfo, UserRequest request);

    User findUserById(Long id);

    Optional<User> findIfUserExistsByEmail(String email);

    void createUser(UserRequest request);

    void verifyUserByEmail(String email);
}
