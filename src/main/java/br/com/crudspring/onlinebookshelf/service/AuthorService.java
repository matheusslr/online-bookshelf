package br.com.crudspring.onlinebookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.crudspring.onlinebookshelf.controller.AuthorController;
import br.com.crudspring.onlinebookshelf.domain.Author;
import br.com.crudspring.onlinebookshelf.domain.Book;
import br.com.crudspring.onlinebookshelf.exceptions.BadRequestExeception;
import br.com.crudspring.onlinebookshelf.mapper.AuthorMapper;
import br.com.crudspring.onlinebookshelf.repository.AuthorRepository;
import br.com.crudspring.onlinebookshelf.repository.BookRepository;
import br.com.crudspring.onlinebookshelf.requests.AuthorPostRequestBody;
import br.com.crudspring.onlinebookshelf.requests.AuthorPutRequestBody;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public List<Author> list() {
        List<Author> authorList = authorRepository.findAll();
        authorList.stream()
                .forEach(a -> a.add(linkTo(methodOn(AuthorController.class).findById(a.getId())).withSelfRel()));
        return authorList;
    }

    public Author findById(long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeception("Author not found"));
        author.add(linkTo(methodOn(AuthorController.class).findById(id)).withSelfRel());
        return author;
    }

    public Author save(AuthorPostRequestBody authorPostRequestBody) {
        Author savedAuthor = authorRepository.save(AuthorMapper.INSTANCE.toAuthor(authorPostRequestBody));
        savedAuthor.add(linkTo(methodOn(AuthorController.class).findById(savedAuthor.getId())).withSelfRel());
        return savedAuthor;
    }

    public void delete(long id) {
        authorRepository.delete(findById(id));
    }

    public void replace(AuthorPutRequestBody authorPutRequestBody) {
        Author author = findById(authorPutRequestBody.getId());
        author.setName(authorPutRequestBody.getName());
        Optional<Book> books = bookRepository.findById(authorPutRequestBody.getBookId());
        books.ifPresent(author::setBook);
        authorRepository.save(author);
        author.add(linkTo(methodOn(AuthorController.class).findById(author.getId())).withSelfRel());
    }
}
