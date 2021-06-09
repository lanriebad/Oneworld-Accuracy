package com.test.assessment.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoMailLogResponse implements Serializable {
    private Long id;

    private String trackingId;

}
