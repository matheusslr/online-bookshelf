package br.com.crudspring.onlinebookshelf.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.crudspring.onlinebookshelf.controller.BookController;
import br.com.crudspring.onlinebookshelf.domain.Author;
import br.com.crudspring.onlinebookshelf.domain.Book;
import br.com.crudspring.onlinebookshelf.exceptions.BadRequestExeception;
import br.com.crudspring.onlinebookshelf.mapper.BookMapper;
import br.com.crudspring.onlinebookshelf.repository.AuthorRepository;
import br.com.crudspring.onlinebookshelf.repository.BookRepository;
import br.com.crudspring.onlinebookshelf.requests.BookPostRequestBody;
import br.com.crudspring.onlinebookshelf.requests.BookPutRequestBody;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public List<Book> listAll() {
        List<Book> bookList = bookRepository.findAll();
        bookList.stream()
                .forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getId())).withSelfRel()));
        return bookList;
    }

    public Book findById(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeception("Book not found"));
        book.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return book;
    }

    public Book save(BookPostRequestBody bookPostRequestBody) {
        Book book = BookMapper.INSTANCE.toBook(bookPostRequestBody);
        Optional<Author> authors = authorRepository.findById(bookPostRequestBody.getAuthorId());
        authors.ifPresent(book::setAuthor);

        Book savedBook = bookRepository.save(book);
        savedBook.add(linkTo(methodOn(BookController.class).findById(savedBook.getId())).withSelfRel());
        return savedBook;
    }

    public void delete(long id) {
        bookRepository.delete(findById(id));
    }

    public void replace(BookPutRequestBody bookPutRequestBody) {
        Book book = BookMapper.INSTANCE.toBook(bookPutRequestBody);
        bookRepository.save(book);
        book.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());
    }
}
