package com.example.voting_system.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voting_system.model.VoteItem;
import com.example.voting_system.model.VoteRecord;
import com.example.voting_system.repository.VoteItemRepository;
import com.example.voting_system.repository.VoteRecordRepository;

@Service
public class VoteService {

    @Autowired
    private VoteItemRepository itemRepo;

    @Autowired
    private VoteRecordRepository recordRepo;

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

        VoteItem item = new VoteItem();

        item.setName(name);

        return itemRepo.save(item);
    }

    @Transactional
    public VoteRecord vote(
            String voterName,
            Long voteItemId) {

        VoteRecord record = new VoteRecord();

        record.setVoterName(voterName);
        record.setVoteItemId(voteItemId);

        return recordRepo.save(record);
    }
    @Transactional
    public void deleteItem(Long id) {

        recordRepo.deleteByVoteItemId(id);

        itemRepo.deleteById(id);
}   
}