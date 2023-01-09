package br.com.crudspring.onlinebookshelf.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorPostRequestBody {
    @NotBlank(message = "Author name cannot be null or empty")
    private String name;
}
