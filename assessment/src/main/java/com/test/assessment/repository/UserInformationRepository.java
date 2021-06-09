package com.test.assessment.repository;

import com.test.assessment.dto.UserResponse;
import com.test.assessment.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<User, Serializable> {

   // @Query(value = "SELECT * FROM User u ", nativeQuery = true)
   // @Query("select new (u.datedeactivated, u.firstname,u.lastname,u.status,u.title,u.dateregistered,u.dateverified,u.email,u.mobile,u.verified,u.role.name) from User u")


    Optional<User> findById(Long id);

    @Query(value ="select u from User u where u.id=:id")
    User findBUserById(@Param("id")Long id);

    Optional<User> findByEmail(String email);
}
