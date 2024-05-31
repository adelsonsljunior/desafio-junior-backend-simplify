package com.adelsonsljunior.desafiojuniorbackendsimplify.controller;

import com.adelsonsljunior.desafiojuniorbackendsimplify.dtos.todo.TodoCreateDTO;
import com.adelsonsljunior.desafiojuniorbackendsimplify.dtos.todo.TodoUpdateDTO;
import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.BaseResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo.ListTodoResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo.SingleTodoResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.service.TodoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid TodoCreateDTO data) {

        Todo newTodo = new Todo(data);

        Todo createdTodo = todoService.create(newTodo);

        BaseResponse response = new SingleTodoResponse("Todo Created Successfully", 201, createdTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<BaseResponse> findAll() {

        List<Todo> todos = todoService.findAll();

        BaseResponse response = new ListTodoResponse("Todo List", 200, todos);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {

        Optional<Todo> todoFound = todoService.findById(id);

        if (todoFound.isEmpty()) {
            BaseResponse responseNotFound = new BaseResponse("Todo Not Found", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }

        BaseResponse responseTodo = new SingleTodoResponse("Todo Found Successfully", 200, todoFound.get());
        return ResponseEntity.ok(responseTodo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody @Valid TodoUpdateDTO data) {

        Optional<Todo> todoFound = todoService.findById(id);

        if (todoFound.isEmpty()) {
            BaseResponse responseNotFound = new BaseResponse("Todo Not Found", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }

        Todo todo = todoFound.get();

        todo.setName(data.name());
        todo.setDescription(data.description());
        todo.setDone(data.done());
        todo.setPriority(data.priority());

        Todo updatedTodo = todoService.update(todo);

        BaseResponse response = new SingleTodoResponse("Todo Updated Successfully", 200, updatedTodo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {

        Optional<Todo> todoFound = todoService.findById(id);

        if (todoFound.isEmpty()) {
            BaseResponse responseNotFound = new BaseResponse("Todo Not Found", 404);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }

        todoService.delete(todoFound.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}