package com.test.assessment.configuration;

public interface ApplicationUrl {

    String BASE_URL = "/api";

    String CREATE_USER_URL = "/user";
    String GET_PAGINATED_USERS_URL = "/users";
    String VERIFY_USER_URL = "/verify";
    String DEACTIVATE_USER_BY_ID_URL = "/user/id/{id}";
    String UPDATE_USER_BY_ID_URL = "/user/id/{id}";



}
