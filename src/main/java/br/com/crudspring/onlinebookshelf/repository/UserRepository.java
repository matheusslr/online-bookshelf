package br.com.crudspring.onlinebookshelf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crudspring.onlinebookshelf.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
}
