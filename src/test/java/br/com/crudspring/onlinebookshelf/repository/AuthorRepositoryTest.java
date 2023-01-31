package br.com.crudspring.onlinebookshelf.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.crudspring.onlinebookshelf.domain.Author;
import jakarta.validation.ConstraintViolationException;

@DataJpaTest
@DisplayName("Test for Author Repository")
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Save persists Author when Sucessfull")
    void save_PersistAuthor_WhenSucessfull(){
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);

        assertNotNull(authorSaved.getId());
        assertNotNull(authorSaved.getName());
        assertEquals(authorSaved.getName(), authorToBeSaved.getName());
        assertEquals(authorSaved.getId(), authorToBeSaved.getId());
    }
    
    @Test
    @DisplayName("Save updates Author when Sucessfull")
    void save_UpdateAuthor_WhenSucessfull(){
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);

        authorSaved.setName("Machado de Assis");

        Author authorUpdated = this.authorRepository.save(authorSaved);

        assertNotNull(authorUpdated.getId());
        assertNotNull(authorUpdated.getName());
        assertEquals(authorUpdated.getName(), authorSaved.getName());
        assertEquals(authorUpdated.getId(), authorSaved.getId());
    }


    @Test
    @DisplayName("Save throw Constraint Violation Exception when name is Empty")
    void save_ThrowsConstraintViolationException_WhenNameIsEmpty(){
        Author author = new Author();

        assertThrows(ConstraintViolationException.class, () -> this.authorRepository.save(author));
    }

    @Test
    @DisplayName("Delete removes Author when Sucessfull")
    void delete_RemovesAuthor_WhenSucessfull(){
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        
        this.authorRepository.delete(authorSaved);

        Optional<Author> authorOptional = this.authorRepository.findById(authorSaved.getId());

        assertTrue(authorOptional.isEmpty());
    }

    @Test
    @DisplayName("Find by name return a list of Author when Sucessfull")
    void findByName_ReturnListOfAuthor_WhenSucessfull(){
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        
        List<Author> authorsFound = this.authorRepository.findByName(authorSaved.getName());

        assertFalse(authorsFound.isEmpty());
        assertTrue(authorsFound.contains(authorSaved));
    }

    private Author createAuthor(){
        return Author.builder()
        .name("Andrzej Sapkowski")
        .build();
    }
    
}
