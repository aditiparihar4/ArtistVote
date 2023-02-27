package com.poc.artist.vote.service;

import com.poc.artist.vote.Utility;
import com.poc.artist.vote.entity.Artist;
import com.poc.artist.vote.entity.Vote;
import com.poc.artist.vote.repository.VoteRepo;
import com.poc.artist.vote.service.impl.VoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteServiceUnitTest {

    @Mock
    private VoteRepo voteRepo;

    @InjectMocks
    private VoteService voteService = new VoteServiceImpl();
    Vote vote = Utility.buildTestVote();

    @Test
    void addVote_Test(){
        when(voteRepo.save(any(Vote.class))).thenReturn(vote);
        Vote vote1 = voteService.addVote(vote);
        assertEquals(vote1.getArtist_id(),vote.getArtist_id());
        verify(this.voteRepo).save(vote);
    }

    @Test
    void getAllVoteCount_Test(){
        when(voteRepo.findvotes(3)).thenReturn(List.of(vote));
        long votes = voteService.countVote(3);
        assertEquals(1, votes);
        verify(this.voteRepo).findvotes(3);
    }


}
