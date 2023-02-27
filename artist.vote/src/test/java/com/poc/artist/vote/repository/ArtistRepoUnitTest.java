//package com.poc.artist.vote.repository;
//
//import com.poc.artist.vote.entity.Artist;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@DataJpaTest
//@AutoConfigureTestDatabase
//class ArtistRepoUnitTest {
//    @Autowired
//    private ArtistRepo artistRepo;
//
//    @Test
//    void getArtist_Success(int artist_id) {
//        Optional<Artist> artist = artistRepo.findById(1);
//        assertTrue(artist.isPresent());
//    }
//
//    @Test
//    void getArtist_failure(int artist_id) {
//        Optional<Artist> artist = artistRepo.findById(3);
//        assertFalse(artist.isPresent());
//    }
//
//    @Test
//    void getAllArtist_Success() {
//        List<Artist> artist = artistRepo.findAll();
//        assertTrue(artist.size() == 2);
//    }
//}