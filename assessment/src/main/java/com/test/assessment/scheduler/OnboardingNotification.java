package com.test.assessment.scheduler;

import com.test.assessment.dao.UserInformationDAO;
import com.test.assessment.dto.UserInfoMailLogResponse;
import com.test.assessment.dto.UserResponse;
import com.test.assessment.model.UserInfoMailLogs;
import com.test.assessment.repository.UserInfoMailLogsRepository;
import com.test.assessment.service.EmailService;
import com.test.assessment.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@EnableScheduling
public class OnboardingNotification {

    final static Logger LOG = LoggerFactory.getLogger(OnboardingNotification.class);

    @Autowired
    private UserInformationDAO userInformationDAO;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserInfoMailLogsRepository userInfoMailLogsRepository;

    @Value("${assessment.test.for.senders.name:test}")
    private String sendersName;

    @Scheduled(fixedDelayString = "${assessment.test.for.scheduler.timer:1800}")
    public void onboardingNotificationJob(){
        LOG.info("about to check for Verified and Deactivated Users");
        List<UserResponse> responseList = userInformationDAO.getVerifiedAndDeactivatedUsers();
        if (responseList.size() > 0) {
            notification(responseList);
        } else {
            LOG.info("No verified or deactivated user");
        }
    }

    private void notification(List<UserResponse> responseList) {
        if (!responseList.isEmpty()) {
            responseList.stream().forEach(response -> {
                    String trackingId = String.format("%s%s", response.getEmail(), response.getStatus());
                    List<UserInfoMailLogResponse> userMailLogs = userInformationDAO.findByTrackingId(trackingId);
                    if (userMailLogs.isEmpty()) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Find Below Status of the User <br/>");
                        stringBuilder.append(String.format("Name: %s %s <br/>", response.getFirstname(), response.getLastname()));
                        stringBuilder.append(String.format("Status of User: %s <br/>", response.getStatus()));
                        emailService.sendEmail(response.getEmail(), response.getTitle(), stringBuilder.toString(),sendersName);
                        LOG.info("Email is being sent and record is about to be inserted into userLogs");
                        insertIntoUserLogs(trackingId);

                    }else{
                        LOG.info("There is No email to be sent");
                    }

            });
        }

    }

    private boolean insertIntoUserLogs(String trackingId) {
        try {
            UserInfoMailLogs logs = new UserInfoMailLogs();
            logs.setTrackingId(trackingId);
            userInfoMailLogsRepository.save(logs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }




}
