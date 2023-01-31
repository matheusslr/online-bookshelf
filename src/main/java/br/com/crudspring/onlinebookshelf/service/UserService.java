package br.com.crudspring.onlinebookshelf.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.crudspring.onlinebookshelf.controller.UserController;
import br.com.crudspring.onlinebookshelf.domain.Book;
import br.com.crudspring.onlinebookshelf.domain.User;
import br.com.crudspring.onlinebookshelf.exceptions.BadRequestExeception;
import br.com.crudspring.onlinebookshelf.mapper.UserMapper;
import br.com.crudspring.onlinebookshelf.repository.BookRepository;
import br.com.crudspring.onlinebookshelf.repository.UserRepository;
import br.com.crudspring.onlinebookshelf.requests.UserPostRequestBody;
import br.com.crudspring.onlinebookshelf.requests.UserPutRequestBody;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> list() {
        List<User> userList = userRepository.findAll();
        userList.stream()
                .forEach(u -> u.add(linkTo(methodOn(UserController.class).findById(u.getId())).withSelfRel()));
        return userList;
    }

    public User findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeception("User not found"));
        user.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return user;
    }

    public User save(UserPostRequestBody userPostRequestBody) {
        User user = UserMapper.INSTANCE.toUser(userPostRequestBody);
        Optional<Book> books = bookRepository.findById(userPostRequestBody.getBookId());
        books.ifPresent(book -> user.setBooks(Set.of(book)));

        User userSaved = userRepository.save(user);
        userSaved.add(linkTo(methodOn(UserController.class).findById(userSaved.getId())).withSelfRel());
        return userSaved;
    }

    public void replace(UserPutRequestBody userPutRequestBody) {
        User user = UserMapper.INSTANCE.toUser(userPutRequestBody);
        userRepository.save(user);
        user.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
    }

    public void remove(Long id){
        userRepository.delete(findById(id));
    }
}
