package com.example.QuizTask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Quiz {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;


    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public Quiz() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String text;

    public Quiz(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }

    @NotNull
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "OPTIONS", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "options")
    @Size(min = 4)
    @OrderColumn
    private List<String>  options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
