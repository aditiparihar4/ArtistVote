package com.poc.artist.vote.service;

import com.poc.artist.vote.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface VoteService {

    Vote addVote(Vote vote);

   long countVote(int artist_id);
}
