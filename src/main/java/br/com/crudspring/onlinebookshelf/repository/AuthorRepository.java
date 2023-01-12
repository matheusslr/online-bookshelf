package br.com.crudspring.onlinebookshelf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crudspring.onlinebookshelf.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
 
    public List<Author> findByName(String name);
}
