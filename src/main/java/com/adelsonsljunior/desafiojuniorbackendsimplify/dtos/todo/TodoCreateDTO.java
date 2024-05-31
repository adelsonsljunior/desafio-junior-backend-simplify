package com.adelsonsljunior.desafiojuniorbackendsimplify.dtos.todo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoCreateDTO(
        @NotBlank(message = "{name.not.blank}") @Size(max = 60, message = "{name.size}") String name,
        @Size(max = 120, message = "{description.size}") String description,
        @Min(value = 1, message = "{priority.min}") @Max(value = 5, message = "{priority.max}") int priority) {
}
