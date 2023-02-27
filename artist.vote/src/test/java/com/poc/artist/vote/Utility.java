package com.poc.artist.vote;

import com.poc.artist.vote.entity.Artist;
import com.poc.artist.vote.entity.Vote;

import java.sql.Date;

public class Utility {

    public static Artist buildTestArtist(){
        Artist artist = new Artist();
        artist.setArtist_id(3);
        artist.setArtist_name("Test Artist");
        artist.setArtist_label("tsest");
        artist.setArtist_description("Test");
        artist.setIs_active(1);
        artist.setVote_count("10");
        artist.setCreate_date(Date.valueOf("2023-02-25"));
        artist.setUpdate_date(Date.valueOf("2023-02-25"));
        return artist;
    }
    public static Artist buildTestArtist1(){
        Artist artist = new Artist();
        artist.setArtist_id(3);
        artist.setArtist_name("Test");
        artist.setArtist_label("test");
        return artist;
    }

    public static Vote buildTestVote(){
        Vote vote = new Vote();
        vote.setArtist_id(3);
        vote.setCreate_date("2023-02-25");
        vote.setUpdate_date("2023-02-25");
        return vote;
    }
}
