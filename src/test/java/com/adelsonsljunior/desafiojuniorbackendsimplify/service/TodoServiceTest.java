package com.adelsonsljunior.desafiojuniorbackendsimplify.service;

import com.adelsonsljunior.desafiojuniorbackendsimplify.model.Todo;
import com.adelsonsljunior.desafiojuniorbackendsimplify.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static com.adelsonsljunior.desafiojuniorbackendsimplify.commom.TodoConstants.INVALID_TODO;
import static com.adelsonsljunior.desafiojuniorbackendsimplify.commom.TodoConstants.TODO;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test
    public void createTodo_WithDataValid_ReturnsTodo() {

        when(todoRepository.save(TODO)).thenReturn(TODO);

        Todo sut = todoService.create(TODO);

        assertThat(sut).isEqualTo(TODO);
    }

    @Test
    public void createTodo_WithDataInvalid_ThrowsException() {

        when(todoRepository.save(INVALID_TODO)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> todoService.create(INVALID_TODO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void findTodo_ByExistingId_ReturnsTodo() {

        when(todoService.findById(1L)).thenReturn(Optional.of(TODO));

        Optional<Todo> sut = todoService.findById(1L);

        assertThat(sut).isNotEmpty();
        assertThat(sut).isEqualTo(Optional.of(TODO));
    }

    @Test
    public void findTodo_ByUnexistingId_ReturnsEmpty() {

        when(todoService.findById(1L)).thenReturn(Optional.empty());

        Optional<Todo> sut = todoService.findById(1L);

        assertThat(sut).isEmpty();
    }

}