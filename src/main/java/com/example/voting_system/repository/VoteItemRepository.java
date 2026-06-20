package com.example.voting_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.voting_system.model.VoteItem;

public interface VoteItemRepository
        extends JpaRepository<VoteItem, Long> {
}