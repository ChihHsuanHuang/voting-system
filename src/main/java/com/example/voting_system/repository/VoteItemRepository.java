package com.example.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.example.voting_system.model.VoteItem;

public interface VoteItemRepository
        extends JpaRepository<VoteItem, Long> {
// 呼叫 MySQL Stored Procedure
    @Procedure(procedureName = "AddVoteItem")
    void AddVoteItem(String p_name);
}