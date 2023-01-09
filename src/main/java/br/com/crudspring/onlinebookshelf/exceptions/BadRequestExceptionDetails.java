package br.com.crudspring.onlinebookshelf.exceptions;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String title;
    private LocalDateTime timestamp;
    private int status;
    private String details;
}