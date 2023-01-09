package br.com.crudspring.onlinebookshelf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crudspring.onlinebookshelf.domain.Author;
import br.com.crudspring.onlinebookshelf.requests.AuthorPostRequestBody;
import br.com.crudspring.onlinebookshelf.requests.AuthorPutRequestBody;
import br.com.crudspring.onlinebookshelf.service.AuthorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> list(){
        return ResponseEntity.ok(authorService.list());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Author> findById(@PathVariable long id){
        return ResponseEntity.ok(authorService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Author> save(@RequestBody @Valid AuthorPostRequestBody authorPostRequestBody){
        return new ResponseEntity(authorService.save(authorPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid AuthorPutRequestBody authorPutRequestBody){
        authorService.replace(authorPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
