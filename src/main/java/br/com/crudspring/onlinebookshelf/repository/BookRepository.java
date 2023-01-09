package br.com.crudspring.onlinebookshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crudspring.onlinebookshelf.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
