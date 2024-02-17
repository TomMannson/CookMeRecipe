package com.tommannson.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class BooksController {

    @Autowired
    private TodoRepository repository;

//    public BooksController(TodoRepository repository) {
//        this.repository = repository;
//    }


    @GetMapping("createpost")
    public void create() {
        Todo asdasd = new Todo();
        asdasd.setName("Test");
        asdasd.setContent("asdasdjklasj dklasjd klasjd lkjasldj");
        repository.save(asdasd);
    }


    @GetMapping("todos")
    public List<Todo> findAll() {
        return repository.findAll();
    }
}