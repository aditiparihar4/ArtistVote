package com.poc.artist.vote.repository;

import com.poc.artist.vote.entity.Artist;
import com.poc.artist.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist,Integer> {

    @Query("SELECT t FROM Artist t WHERE t.artist_label = ?1")
    List<Artist> findArtistBylabel(String artist_id);

}
