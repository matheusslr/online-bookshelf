package br.com.crudspring.onlinebookshelf.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthorPutRequestBody {
    @NotNull(message = "Author id cannot be null")
    private Long id;
    @NotNull(message = "Book id cannot be null")
    private Long bookId;
}
