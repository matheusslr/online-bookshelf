package br.com.crudspring.onlinebookshelf.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.crudspring.onlinebookshelf.domain.User;
import br.com.crudspring.onlinebookshelf.requests.UserPostRequestBody;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserPostRequestBody userPostRequestBody);
}
