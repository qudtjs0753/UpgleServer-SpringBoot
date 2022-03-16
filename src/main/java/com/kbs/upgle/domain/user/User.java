package com.kbs.upgle.domain.user;

import com.kbs.upgle.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kbs
 */

@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "sns_id_and_sns_type", columnNames = {
        "snsId", "snsType"
}),@UniqueConstraint(columnNames = {"email","nickname"})})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String email;

    @Setter
    @Column
    private String password;

    @Setter
    @Column
    private String nickname;

    @Setter
    @Column
    private String image;

    @Setter
    @Column(length = 500)
    private String introduction;

    @Setter
    @Column(length = 20)
    private String department;

    @Setter
    @Column(length=200)
    private String wellTalent;

    @Setter
    @Column(length=200)
    private String interestTalent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "following")
    private User following;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "following")
    private List<User> followingList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="follower")
    private User follower;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "follower")
    private List<User> followerList = new ArrayList<>();

    @Setter
    @Column(length=200)
    private Long snsId;

    @Setter
    @Column
    private String snsType;

    public void addFollowing(User toFollow) {
        this.followingList.add(toFollow);
    }

    public void addFollower(User newFollower){
        followerList.add(newFollower);
    }

    @Builder
    protected User(String email, String nickname, String password) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static User of(String email, String nickname, String password){
        return new User(email, nickname, password);
    }
}
