package com.tommannson.todoapp;

import jakarta.persistence.*;


@Entity
public class Todo {
    @Id
    @SequenceGenerator(
            name = "todo_pk_generator_seq",
            sequenceName = "todo_id_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "todo_pk_generator_seq")
    private Long id;
    private String name;
    private String content;

    public Todo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
