package com.adelsonsljunior.desafiojuniorbackendsimplify.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationErrorResponse extends BaseResponse {

    @JsonProperty("erros")
    private List<String> erros;


    public ValidationErrorResponse(String message, int code, String status, List<String> erros) {
        super(message, code, status);
        this.erros = erros;
    }
}
