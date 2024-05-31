package com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo;

import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleTodoResponse extends BaseResponse {

    @JsonProperty("data")
    private Todo data;

    public SingleTodoResponse(String message, int code, String status, Todo data) {
        super(message, code, status);
        this.data = data;
    }
}
