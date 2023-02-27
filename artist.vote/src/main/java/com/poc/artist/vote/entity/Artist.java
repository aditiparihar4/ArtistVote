package com.poc.artist.vote.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "artist")

public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artist_id;
    private String artist_label;
    private String artist_name;
    private String artist_description;

    private String vote_count;
    private int is_active;
    private Date create_date;
    private Date update_date;
}
