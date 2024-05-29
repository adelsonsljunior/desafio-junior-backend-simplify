package com.adelsonsljunior.desafiojuniorbackendsimplify.service;
import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo todo){
        return todoRepository.save(todo);
    }

    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    public Optional<Todo> findById(Long id){
        return todoRepository.findById(id);
    }

    public Todo update(Todo todo){
        return todoRepository.save(todo);
    }

    public void delete(Todo todo){
        todoRepository.delete(todo);
    }

}
