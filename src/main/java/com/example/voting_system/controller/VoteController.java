package com.example.voting_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.voting_system.model.VoteItem;
import com.example.voting_system.model.VoteRecord;
import com.example.voting_system.service.VoteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/items")
    public List<Map<String, Object>> getItems() {
        return voteService.getItems();
    }

    @PostMapping("/items")
    public VoteItem addItem(
            @RequestBody Map<String,String> req) {

        return voteService.addItem(
                req.get("name"));
    }

    @PostMapping("/vote")
    public VoteRecord vote(
            @RequestBody Map<String,String> req) {

        return voteService.vote(
                req.get("voterName"),
                Long.parseLong(
                        req.get("voteItemId"))
        );
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(
        @PathVariable Long id) {

    voteService.deleteItem(id);
}
}