package com.example.voting_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vote_item")
public class VoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public VoteItem() {}

    public VoteItem(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}