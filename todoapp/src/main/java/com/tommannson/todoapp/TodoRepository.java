package com.tommannson.todoapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findByName(String name);
}