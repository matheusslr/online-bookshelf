package br.com.crudspring.onlinebookshelf.requests;

import org.hibernate.validator.constraints.Range;

import br.com.crudspring.onlinebookshelf.annotations.ISBNContraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookPostRequestBody {
    @NotBlank(message = "Book name cannot be empty or null")
    private String name;

    @Range(min = 0, max = 2023)
    @NotNull(message = "Book publish date cannot be null")
    private Integer publishDate;

    @NotNull(message = "Author id cannot be null")
    private Long authorId;

    private String publishers;

    private String language;

    private Integer numberPages;

    @NotNull(message = "ISBN book's cannot be null")
    @ISBNContraints
    private Long isbn;

    private String description;
}
