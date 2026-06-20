package com.example.voting_system.service;

import java.util.*;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voting_system.model.VoteItem;
import com.example.voting_system.model.VoteRecord;
import com.example.voting_system.repository.VoteItemRepository;
import com.example.voting_system.repository.VoteRecordRepository;

@Service
public class VoteService {

    private final VoteItemRepository itemRepo;

    private final VoteRecordRepository recordRepo;

    public VoteService(
            VoteItemRepository itemRepo,
            VoteRecordRepository recordRepo) {

        this.itemRepo = itemRepo;
        this.recordRepo = recordRepo;
    }
    public List<Map<String, Object>> getItems() {

        List<Map<String, Object>> result = new ArrayList<>();

        for (VoteItem item : itemRepo.findAll()) {

            Map<String, Object> map = new HashMap<>();

            map.put("id", item.getId());
            map.put("name", item.getName());
            map.put(
                "votes",
                recordRepo.countByVoteItemId(item.getId())
            );

            result.add(map);
        }

        return result;
    }


    public VoteItem addItem(String name) {

        itemRepo.AddVoteItem(name);

        return null;
    }

    
    @Transactional
    public VoteRecord vote(
            String voterName,
            Long voteItemId) {

        recordRepo.AddVoteRecord(
                voterName,
                voteItemId
        );

        return null;
    }

    @Transactional
    public void deleteItem(Long id) {

        recordRepo.deleteByVoteItemId(id);

        itemRepo.deleteById(id);
    }
}