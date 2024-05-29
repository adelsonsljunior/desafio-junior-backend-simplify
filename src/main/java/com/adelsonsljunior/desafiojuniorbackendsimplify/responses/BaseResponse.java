package com.adelsonsljunior.desafiojuniorbackendsimplify.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int status;

    public BaseResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
