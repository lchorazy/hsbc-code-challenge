package com.hsbc.lukasz.networkingapp.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "Post")
@Data
@NoArgsConstructor
public class DbPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private DbUser user;

    private String message;

    private LocalDateTime created;

    public DbPost(DbUser user, String message) {
        this.user = user;
        this.message = message;
        this.created = LocalDateTime.now();
    }
}
