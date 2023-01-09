package br.com.crudspring.onlinebookshelf.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPostRequestBody {
    @NotBlank(message = "Name field cannot be empty or null")
    private String name;
    @Email(message = "Invalid email, check documentation")
    @NotBlank(message = "Email field cannot be empty or null")
    private String email;
    @NotNull(message = "Book id field cannot be empty or null")
    private Long bookId;
}
