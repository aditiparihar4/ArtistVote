package com.poc.artist.vote.controller;

import com.poc.artist.vote.entity.Artist;
import com.poc.artist.vote.entity.ArtistVoteResponse;
import com.poc.artist.vote.entity.Vote;
import com.poc.artist.vote.exception.ArtistNotFoundException;
import com.poc.artist.vote.service.ArtistService;
import com.poc.artist.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ArtistVoteController {

    @Autowired
    ArtistService artistService;

    @Autowired
    VoteService voteService;

    @GetMapping("/showAll")
    public List<Artist> getAllArtistDetails(){
        List<Artist> artistList = artistService.getAllArtist();
        artistList.stream().forEach(p->p.setVote_count(String.valueOf(voteService.countVote(p.getArtist_id()))));
        return artistList;
    }

    @GetMapping("/getArtist/{artist_label}")
    public List<Artist> getArtistDetailByID(@PathVariable String artist_label){
        System.out.println("path variable : "+ artist_label);
        List<Artist> artistList = artistService.getArtistByLabel(artist_label.trim().toLowerCase());
        System.out.println("artistList : "+ artistList);
        if(CollectionUtils.isEmpty(artistList))
            throw new ArtistNotFoundException("artist_label - " + artist_label);

        Artist artist = artistList.get(0);
        artist.setVote_count(String.valueOf(voteService.countVote(artist.getArtist_id())));
        return artistList;
    }

    @PostMapping("/addVote")
    public ResponseEntity<ArtistVoteResponse> addVote(@RequestBody Vote vote){
        if(Objects.isNull(vote)){
            return new ResponseEntity<>(createResponse("Invalid Request"), HttpStatus.OK);
        }
        Optional<Artist> artist = artistService.getArtist(vote.getArtist_id());
        if(artist.isEmpty())
            throw new ArtistNotFoundException("artist_label - " + vote.getArtist_id()
            );

        voteService.addVote(vote);
        return new ResponseEntity<>(createResponse("Success"), HttpStatus.OK);
    }

    public ArtistVoteResponse createResponse(String message){
        ArtistVoteResponse response = new ArtistVoteResponse();
        response.setMessage(message);
        response.setDate(java.time.LocalDateTime.now());
        return response;
    }


}
