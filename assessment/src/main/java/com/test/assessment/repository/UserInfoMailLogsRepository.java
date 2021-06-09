package com.test.assessment.repository;

import com.test.assessment.model.UserInfoMailLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;


@Repository
public interface UserInfoMailLogsRepository extends JpaRepository<UserInfoMailLogs, Serializable> {
}
