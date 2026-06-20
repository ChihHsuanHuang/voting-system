package com.example.voting_system.controller;

import java.util.List;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import com.example.voting_system.model.VoteItem;
//import com.example.voting_system.model.VoteRecord;
import com.example.voting_system.service.VoteService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VoteController {

    private final VoteService voteService;
    public VoteController(
            VoteService voteService) {

        this.voteService = voteService;
    }
    // 查詢所有項目
    @GetMapping("/items")
    public List<Map<String, Object>> getItems() {
        return voteService.getItems();
    }
    // 新增項目
    @PostMapping("/items")
    public void addItem(
        @RequestBody Map<String,String> req) {

        voteService.addItem(
            req.get("name"));
}
    // 投票
    @PostMapping("/vote")
    public void vote(
        @RequestBody Map<String,String> req) {

        voteService.vote(
            req.get("voterName"),
            Long.parseLong(
                req.get("voteItemId"))
    );
}
    // 刪除項目
    @DeleteMapping("/items/{id}")
    public void deleteItem(
        @PathVariable Long id) {

    voteService.deleteItem(id);
}
}