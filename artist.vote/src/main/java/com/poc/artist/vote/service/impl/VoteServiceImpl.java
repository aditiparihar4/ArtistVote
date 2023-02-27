package com.poc.artist.vote.service.impl;

import com.poc.artist.vote.entity.Vote;
import com.poc.artist.vote.repository.VoteRepo;
import com.poc.artist.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class VoteServiceImpl implements VoteService {
    @Autowired
    VoteRepo voteRepo;
    @Override
    public Vote addVote(Vote vote) {
        voteRepo.save(vote);
        return vote;
    }

    @Override
    public long countVote(int artist_id) {
        List<Vote> voteList =  voteRepo.findvotes(artist_id);
        return voteList.size();
    }




}
