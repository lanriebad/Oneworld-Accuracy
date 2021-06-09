package com.test.assessment.controller;


import com.test.assessment.configuration.ApplicationUrl;
import com.test.assessment.dao.UserInformationDAO;
import com.test.assessment.dto.UserRequest;
import com.test.assessment.model.User;
import com.test.assessment.service.UserInformationService;
import com.test.assessment.utils.DefaultServiceResponse;
import com.test.assessment.utils.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(ApplicationUrl.BASE_URL)
@CrossOrigin(origins = "*")
public class UserInformationController {

    @Autowired
    UserInformationService userInformationService;

    @org.springframework.beans.factory.annotation.Value("${assessment.test.for.verification.url:http://localhost:8085/api/verify?email=%s}")
    String verificationUrl;


    @org.springframework.beans.factory.annotation.Value("${assessment.test.for.verification.message:Your Profile has been verified}")
    String verificationMessage;

    @org.springframework.beans.factory.annotation.Value("${assessment.test.for.message:Kindly click on the link to verify your profile}")
    String message;



    @PostMapping(value = ApplicationUrl.CREATE_USER_URL)
    public DefaultServiceResponse createUser(@RequestBody UserRequest request) {
        DefaultServiceResponse defaultServiceResponse = new DefaultServiceResponse();
        Optional<User> user = userInformationService.findIfUserExistsByEmail(request.getEmail());
        if (!user.isPresent()) {
            userInformationService.createUser(request);
            Map verification = new HashMap<>();
            String url = String.format(verificationUrl,request.getEmail());
            verification.put("message",message);
            verification.put("verificationUrl",url);
            defaultServiceResponse.setResponseData(Collections.singletonList(verification));
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.SUCCESS.getCode());
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.SUCCESS.getDefaultMessage());

        } else {
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.USER_ALREADY_EXIST.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.USER_ALREADY_EXIST.getCode());
        }

        return defaultServiceResponse;
    }


    @GetMapping(value = ApplicationUrl.GET_PAGINATED_USERS_URL)
    public DefaultServiceResponse getAllUserRecords(Pageable pageable) {
        DefaultServiceResponse userRecords = userInformationService.getAllUserRecords(pageable);
        return userRecords;
    }

    @GetMapping(value =ApplicationUrl.VERIFY_USER_URL)
    public DefaultServiceResponse verifyUserByEmail(@RequestParam String email){
        DefaultServiceResponse defaultServiceResponse = new DefaultServiceResponse();
        Optional<User> verify = userInformationService.findIfUserExistsByEmail(email);
        if (verify.isPresent()) {
            userInformationService.verifyUserByEmail(email);
            Map verification = new HashMap<>();
            verification.put("message",verificationMessage);
            defaultServiceResponse.setResponseData(Collections.singletonList(verification));
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.SUCCESS.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.SUCCESS.getCode());
        } else {
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.USER_NOT_FOUND.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.USER_NOT_FOUND.getCode());
        }

        return defaultServiceResponse;
    }


    @DeleteMapping(value =ApplicationUrl.DEACTIVATE_USER_BY_ID_URL)
    public DefaultServiceResponse deactivateUserById(@PathVariable Long id)  {
        DefaultServiceResponse defaultServiceResponse = new DefaultServiceResponse();
        Optional<User> deactivate = userInformationService.findIfUserExistsById(id);
        if (deactivate.isPresent()) {
            userInformationService.deactivateUserById(id);
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.SUCCESS.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.SUCCESS.getCode());
        } else {
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.USER_NOT_FOUND.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.USER_NOT_FOUND.getCode());
        }

        return defaultServiceResponse;
    }


    @PutMapping(value = ApplicationUrl.UPDATE_USER_BY_ID_URL)
    public DefaultServiceResponse updateUserById(@PathVariable Long id, @RequestBody UserRequest request) {
        DefaultServiceResponse defaultServiceResponse = new DefaultServiceResponse();
        User userInfo = userInformationService.findUserById(id);
        if (userInfo != null) {
            userInformationService.updateUserById(userInfo, request);
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.SUCCESS.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.SUCCESS.getCode());
        } else {
            defaultServiceResponse.setResponseMsg(ServiceResponse.ResponseCode.USER_NOT_FOUND.getDefaultMessage());
            defaultServiceResponse.setResponseCode(ServiceResponse.ResponseCode.USER_NOT_FOUND.getCode());
        }
        return defaultServiceResponse;

    }




}
