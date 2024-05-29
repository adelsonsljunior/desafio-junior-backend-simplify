package com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo;

import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SingleTodoResponse extends BaseResponse {

    @JsonProperty("data")
    private Todo data;

    public SingleTodoResponse(String message, int status, Todo data) {
        super(message, status);
        this.data = data;
    }
}
