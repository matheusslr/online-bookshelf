package br.com.crudspring.onlinebookshelf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.crudspring.onlinebookshelf.domain.Book;
import br.com.crudspring.onlinebookshelf.requests.BookPostRequestBody;
import br.com.crudspring.onlinebookshelf.requests.BookPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    public static final BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    public abstract Book toBook(BookPostRequestBody postBookRequestBody);

    public abstract Book toBook(BookPutRequestBody bookPutRequestBody);
}
