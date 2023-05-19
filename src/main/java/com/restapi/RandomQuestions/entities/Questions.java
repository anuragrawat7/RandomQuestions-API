package com.restapi.RandomQuestions.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "random_Questions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "answer", columnDefinition = "TEXT NOT NULL")
    private String answer;

    @Column(name = "question", columnDefinition = "TEXT NOT NULL")
    private String question;

    private int value;

    private String airdate;

    private String created_at;

    private String updated_at;

    private int category_id;

    private int game_id;

    private String invalid_count;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "catId", nullable = true)
    private Category category;

}
