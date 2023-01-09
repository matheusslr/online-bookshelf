package br.com.crudspring.onlinebookshelf.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.crudspring.onlinebookshelf.domain.Book;
import br.com.crudspring.onlinebookshelf.domain.User;
import br.com.crudspring.onlinebookshelf.exceptions.BadRequestExeception;
import br.com.crudspring.onlinebookshelf.mapper.UserMapper;
import br.com.crudspring.onlinebookshelf.repository.BookRepository;
import br.com.crudspring.onlinebookshelf.repository.UserRepository;
import br.com.crudspring.onlinebookshelf.requests.UserPostRequestBody;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeception("User not found"));
    }

    public User save(UserPostRequestBody userPostRequestBody) {
        User user = UserMapper.INSTANCE.toUser(userPostRequestBody);
        Optional<Book> books = bookRepository.findById(userPostRequestBody.getBookId());
        books.ifPresent(book -> user.setBooks(Set.of(book)));
        return userRepository.save(user);
    }
}
