package com.poc.artist.vote.service;

import com.poc.artist.vote.Utility;
import com.poc.artist.vote.entity.Artist;
import com.poc.artist.vote.repository.ArtistRepo;
import com.poc.artist.vote.service.impl.ArtistServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ArtistServiceUnitTest {

    @Mock
    private ArtistRepo artistRepo;

    @InjectMocks
    private ArtistService artistService = new ArtistServiceImpl();

    @Test
    void findArtistBylabel_Test(){
        Artist artist = Utility.buildTestArtist();
        when(artistRepo.findArtistBylabel("test")).thenReturn(List.of(artist));
        List<Artist> artistdata = artistService.getArtistByLabel("test");
        assertEquals(artistdata.stream().findFirst().get().getArtist_id(),artist.getArtist_id());
        verify(this.artistRepo).findArtistBylabel("test");
    }

    @Test
    void findArtist_Test(){
        Artist artist = Utility.buildTestArtist();
        when(artistRepo.findById(3)).thenReturn(Optional.of(artist));
        Optional<Artist> artistdata = artistService.getArtist(3);
        assertEquals(artistdata.stream().findFirst().get().getArtist_id(),artist.getArtist_id());
        verify(this.artistRepo).findById(3);
    }

    @Test
    void getAllArtist_Test(){
        Artist artist = Utility.buildTestArtist();
        when(artistRepo.findAll()).thenReturn(List.of(artist));
        List<Artist> artistdata = artistService.getAllArtist();
        assertEquals(1, artistdata.size());
        verify(this.artistRepo).findAll();
    }


}
