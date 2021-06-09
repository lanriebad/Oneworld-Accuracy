package com.test.assessment.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="userinfomaillogs")
public class UserInfoMailLogs implements Serializable {

    @Column(name="trackingid")
    private String trackingId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
