package com.test.assessment.dao;


import com.test.assessment.dto.UserInfoMailLogResponse;
import com.test.assessment.dto.UserResponse;
import com.test.assessment.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserInformationDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @org.springframework.beans.factory.annotation.Value("${assessment.test.for.status.deactivated:DEACTIVATED}")
    String statusDeActivated;


    @org.springframework.beans.factory.annotation.Value("${assessment.test.for.status.verified:VERIFIED}")
    String statusVerified;

    @Autowired
    UserUtils userUtils;


    public void deactivateUserById(Long id) {
        String deactivateUser = String.format("update userinformation set status='" + statusDeActivated + "',datedeactivated='" + userUtils.dateToString() + "', verified='Y' where id= %d",id);
        this.jdbcTemplate.update(deactivateUser);
    }

    public void verifyUserByEmail(String email) {
        String verifyUser = String.format("update userinformation set status= '" + statusVerified + "', dateverified='" + userUtils.dateToString() + "', verified='Y'  where email= '%s'",email);
        this.jdbcTemplate.update(verifyUser);
    }

    public List<UserInfoMailLogResponse> findByTrackingId(String trackingId) {
        List<UserInfoMailLogResponse> userList = new ArrayList<>();
        try {
            String sql = String.format("select id,trackingid from UserInfoMailLogs where trackingId='%s'", trackingId);
            userList = jdbcTemplate.query(sql, new RowMapper<UserInfoMailLogResponse>() {
                @Override
                public UserInfoMailLogResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    UserInfoMailLogResponse response = new UserInfoMailLogResponse();
                    response.setId(rs.getLong("id"));
                    response.setTrackingId(rs.getString("trackingid"));
                    return response;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;

    }

    public List<UserResponse> getVerifiedAndDeactivatedUsers() {
        List<UserResponse> userList = new ArrayList<>();
        try {
            String sql = "select firstname,lastname,email,status,title from userinformation where status='" + statusVerified + "' OR status='" + statusDeActivated + "'";
            userList = jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
                @Override
                public UserResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    UserResponse response = new UserResponse();
                    response.setTitle(rs.getString("title"));
                    response.setEmail(rs.getString("email"));
                    response.setFirstname(rs.getString("firstname"));
                    response.setLastname(rs.getString("lastname"));
                    response.setStatus(rs.getString("status"));

                    return response;

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }




}
