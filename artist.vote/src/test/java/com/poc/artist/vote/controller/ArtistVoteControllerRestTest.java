package com.poc.artist.vote.controller;

import com.poc.artist.vote.Utility;
import com.poc.artist.vote.entity.Artist;
import com.poc.artist.vote.entity.Vote;
import com.poc.artist.vote.service.ArtistService;
import com.poc.artist.vote.service.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArtistVoteController.class)
public class ArtistVoteControllerRestTest {

    @MockBean
    private ArtistService artistService;

    @MockBean
    private VoteService voteService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showAllArtistTest() throws Exception {
        Artist artist = Utility.buildTestArtist();
        when(artistService.getAllArtist()).thenReturn(List.of(artist));

        mockMvc.perform(get("/api/showAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getArtistTest() throws Exception {
        Artist artist = Utility.buildTestArtist1();
        when(artistService.getArtistByLabel("test")).thenReturn(List.of(artist));

        MvcResult result  = mockMvc.perform(MockMvcRequestBuilders.get("/api/getArtist/test"))
                .andExpect(status().isOk())
                .andReturn();
        assertNotNull(result);

    }

    @Test
    void getArtistTest_NotFound() throws Exception {
        when(artistService.getArtistByLabel("test")).thenReturn(null);

        MvcResult result  = mockMvc.perform(MockMvcRequestBuilders.get("/api/getArtist/test"))
                .andExpect(status().isNotFound())
                .andReturn();
        assertNotNull(result);

    }
    @Test
    void  addVoteTest() throws Exception {
        Vote vote = Utility.buildTestVote();
        when(voteService.addVote(any(Vote.class))).thenReturn(vote);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/addVote")
                        .content(String.valueOf(vote))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn();
        String resultCZ = result.getResponse().getContentAsString();
        assertNotNull(resultCZ);
    }

    @Test
    void  addVoteTest_NotFound() throws Exception {
        Vote vote = Utility.buildTestVote();
        vote.setArtist_id(5);
        when(artistService.getArtist(3)).thenReturn(null);
        when(voteService.addVote(any(Vote.class))).thenReturn(vote);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/addVote")
                        .content(String.valueOf(vote))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest())
                .andReturn();
    }

}
