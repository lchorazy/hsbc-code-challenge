package com.hsbc.lukasz.networkingapp.db.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class DbFollow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private DbUser user;

    @OneToOne
    @JoinColumn(name = "followed_user_id", referencedColumnName = "id")
    private DbUser followedUser;

    public DbFollow(DbUser user, DbUser followedUser) {
        this.user = user;
        this.followedUser = followedUser;
    }
}
