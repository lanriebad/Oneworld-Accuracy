package com.test.assessment.utils;

import java.io.Serializable;

public interface ServiceResponse<T extends Serializable> extends Serializable{

    enum ResponseCode {
        ERROR("99", "Operation Error"), USER_NOT_FOUND("01", "USER NOT FOUND"), PENDING("P",
                "Operation Pending"), NO_RECORD_FOUND("03", "NO RECORD FOUND"), SUCCESS("00", "Operation Successful"), INVALID_NAME_OR_PASSWORD("02", "Invalid Name or Password"),USER_ALREADY_EXIST("04", "USER ALREADY EXIST");

        protected String code;

        protected String defaultMessage;



        ResponseCode(String code, String defaultMessage) {
            this.code = code;
            this.defaultMessage = defaultMessage;
        }


        public String getCode() {
            return code;
        }


        public String getDefaultMessage() {
            return defaultMessage;
        }


        @Override
        public String toString() {
            return getCode();
        }
    }


    String getResponseCode();


    String getResponseMsg();


    void setResponseCode(String responseCode);


    void setResponseMsg(String responseMsg);
}
