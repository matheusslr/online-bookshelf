package br.com.crudspring.onlinebookshelf.requests;

import br.com.crudspring.onlinebookshelf.domain.Author;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookPutRequestBody {
    @NotBlank(message = "Book id cannot be null or empty")
    private Long id;
    private String name;
    private Author author;
}
