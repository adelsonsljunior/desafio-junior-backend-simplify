package com.adelsonsljunior.desafiojuniorbackendsimplify.model;
import com.adelsonsljunior.desafiojuniorbackendsimplify.dtos.todo.TodoCreateDTO;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    public String name;
    public String description;
    @Column(columnDefinition = "boolean default false")
    public boolean done;
    @NotNull
    public int priority;

    public Todo(TodoCreateDTO data) {
        this.name = data.name();
        this.description = data.description();
        this.priority = data.priority();
    }

    public Todo(String name, String description, int priority) {
        this.name = name;
        this.description = description;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this);
    }

}
