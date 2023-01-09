package br.com.crudspring.onlinebookshelf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.crudspring.onlinebookshelf.domain.Author;
import br.com.crudspring.onlinebookshelf.requests.AuthorPostRequestBody;
import br.com.crudspring.onlinebookshelf.requests.AuthorPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper {
    public static final AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    public abstract Author toAuthor(AuthorPostRequestBody authorBookRequestBody);

    public abstract Author toAuthor(AuthorPutRequestBody authorPutRequestBody);
}
