package br.com.crudspring.onlinebookshelf.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        return authorRepository.findAll();
    }

    public Author findById(long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeception("Author not found"));
    }

    public Author save(AuthorPostRequestBody authorPostRequestBody) {
        return authorRepository.save(AuthorMapper.INSTANCE.toAuthor(authorPostRequestBody));
    }

    public void delete(long id) {
        authorRepository.delete(findById(id));
    }

    public void replace(AuthorPutRequestBody authorPutRequestBody){
        Author author = findById(authorPutRequestBody.getId());
        Optional<Book> books = bookRepository.findById(authorPutRequestBody.getBookId());
        books.ifPresent(author::setBook);
        authorRepository.save(author);
    }
}
