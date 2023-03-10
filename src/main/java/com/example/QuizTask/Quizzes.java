package com.example.QuizTask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.util.List;

@Entity
public class Quizzes {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "bigint not null")
    private Long id;


    @Column
    @NotNull
    @NotEmpty
    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public Quizzes() {
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

    public Quizzes(String title, String text, List<String> options,
                   List<Integer> answer, String email) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.email = email;
    }

    @NotNull
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "OPTIONS", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "options")
    @Size(min = 2)
//    @OrderColumn
    private List<String> options;


    @ElementCollection
    @CollectionTable(name = "ANSWERS", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "answers")
//    @OrderColumn
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> answer;


    @Column
    @JsonIgnore
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
