package br.com.crudspring.onlinebookshelf.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        return bookRepository.findAll();
    }

    public Book findById(long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeception("Book not found"));
    }

    public Book save(BookPostRequestBody bookPostRequestBody) {
        Book book = BookMapper.INSTANCE.toBook(bookPostRequestBody);
        book.setPublishDate(LocalDate.now());
        Optional<Author> authors = authorRepository.findById(bookPostRequestBody.getAuthorId());
        authors.ifPresent(book::setAuthor);
        return bookRepository.save(book);
    }

    public void delete(long id) {
        bookRepository.delete(findById(id));
    }

    public void replace(BookPutRequestBody bookPutRequestBody) {
        Book book = BookMapper.INSTANCE.toBook(bookPutRequestBody);
        bookRepository.save(book);
    }
}
