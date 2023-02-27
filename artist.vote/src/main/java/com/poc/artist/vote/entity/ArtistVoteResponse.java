package com.poc.artist.vote.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArtistVoteResponse {

    public String message;
    public LocalDateTime date;
}
