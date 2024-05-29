package com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo;

import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListTodoResponse extends BaseResponse {

    @JsonProperty("data")
    private List<Todo> data;

    public ListTodoResponse(String message, int status, List<Todo> todos) {
        super(message, status);
        this.data = todos;
    }
}
