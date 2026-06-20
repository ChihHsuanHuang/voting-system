package com.example.voting_system.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vote_record")
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voter_name")
    private String voterName;

    @Column(name = "vote_item_id")
    private Long voteItemId;

    public VoteRecord() {}

    public Long getId() {
        return id;
    }

    public String getVoterName() {
        return voterName;
    }

    public Long getVoteItemId() {
        return voteItemId;
    }

    public void setVoterName(String voterName) {
        this.voterName = voterName;
    }

    public void setVoteItemId(Long voteItemId) {
        this.voteItemId = voteItemId;
    }
}