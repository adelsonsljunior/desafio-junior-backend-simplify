package com.adelsonsljunior.desafiojuniorbackendsimplify.controller;

import com.adelsonsljunior.desafiojuniorbackendsimplify.dtos.todo.TodoCreateDTO;
import com.adelsonsljunior.desafiojuniorbackendsimplify.dtos.todo.TodoUpdateDTO;
import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.BaseResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.ValidationErrorResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo.ListTodoResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.responses.todo.SingleTodoResponse;
import com.adelsonsljunior.desafiojuniorbackendsimplify.service.TodoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/todos", produces = {"application/json"})
@Tag(name = "todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @Operation(method = "POST", summary = "Create a ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "ToDo Created Successfully", content = @Content(schema = @Schema(implementation = SingleTodoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
    })
    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid TodoCreateDTO data, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return buildValidationError(bindingResult);
        }

        Todo newTodo = new Todo(data);

        Todo createdTodo = todoService.create(newTodo);

        BaseResponse response = new SingleTodoResponse("ToDo Created Successfully", 201, "Created", createdTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(method = "GET", summary = "List all ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All ToDo listed successfully")
    })
    @GetMapping
    public ResponseEntity<ListTodoResponse> findAll() {

        List<Todo> todos = todoService.findAll();

        ListTodoResponse response = new ListTodoResponse("Todo List", 200, "Ok", todos);
        return ResponseEntity.ok().body(response);
    }

    @Operation(method = "GET", summary = "Find a ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ToDo Found Successfully", content = @Content(schema = @Schema(implementation = SingleTodoResponse.class))),
            @ApiResponse(responseCode = "404", description = "ToDo Not Found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable Long id) {

        Optional<Todo> todoFound = todoService.findById(id);

        if (todoFound.isEmpty()) {
            BaseResponse responseNotFound = new BaseResponse("Todo Not Found", 404, "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }

        BaseResponse responseTodo = new SingleTodoResponse("Todo Found Successfully", 200, "Ok", todoFound.get());
        return ResponseEntity.ok(responseTodo);
    }

    @Operation(method = "PATCH", summary = "Update a Todo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todo Updated Successfully", content = @Content(schema = @Schema(implementation = SingleTodoResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Parameters", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Todo Not Found"),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<BaseResponse> update(@PathVariable Long id, @RequestBody @Valid TodoUpdateDTO data, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return buildValidationError(bindingResult);
        }

        Optional<Todo> todoFound = todoService.findById(id);

        if (todoFound.isEmpty()) {
            BaseResponse responseNotFound = new BaseResponse("Todo Not Found", 404, "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }

        Todo todo = todoFound.get();

        todo.setName(data.name());
        todo.setDescription(data.description());
        todo.setDone(data.done());
        todo.setPriority(data.priority());

        Todo updatedTodo = todoService.update(todo);

        BaseResponse response = new SingleTodoResponse("Todo Updated Successfully", 200, "Ok", updatedTodo);
        return ResponseEntity.ok(response);
    }

    @Operation(method = "DELETE", summary = "Delete a ToDo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "ToDo deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "ToDo Not Found"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {

        Optional<Todo> todoFound = todoService.findById(id);

        if (todoFound.isEmpty()) {
            BaseResponse responseNotFound = new BaseResponse("Todo Not Found", 404, "Not Found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseNotFound);
        }

        todoService.delete(todoFound.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ResponseEntity<BaseResponse> buildValidationError(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        BaseResponse errorResponse = new ValidationErrorResponse("Validation Errors", 400, "Bad Request", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}