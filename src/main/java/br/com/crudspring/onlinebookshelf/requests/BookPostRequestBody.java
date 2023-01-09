package br.com.crudspring.onlinebookshelf.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookPostRequestBody {
    @NotBlank(message = "Book name cannot be empty or null")
    private String name;
    @NotNull(message = "Author id cannot be null")
    private Long authorId;
}
