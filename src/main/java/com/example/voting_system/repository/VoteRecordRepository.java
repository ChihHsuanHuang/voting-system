package com.example.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.voting_system.model.VoteRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRecordRepository
        extends JpaRepository<VoteRecord, Long> {

    long countByVoteItemId(Long voteItemId);

    void deleteByVoteItemId(Long voteItemId);
}