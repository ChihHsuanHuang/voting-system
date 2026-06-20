package com.example.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.voting_system.model.VoteRecord;

public interface VoteRecordRepository
        extends JpaRepository<VoteRecord, Long> {

    long countByVoteItemId(Long voteItemId);

    void deleteByVoteItemId(Long voteItemId);

    @Procedure(procedureName = "AddVoteRecord")
    void AddVoteRecord(
            String p_voter_name,
            Long p_vote_item_id
    );
}