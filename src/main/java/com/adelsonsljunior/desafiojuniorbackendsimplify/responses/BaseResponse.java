package com.adelsonsljunior.desafiojuniorbackendsimplify.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private int code;
    @JsonProperty("status")
    private String status;

    public BaseResponse(String message, int code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
