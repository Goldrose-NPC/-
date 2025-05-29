package com.example.classroom.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public User(){

    }
    public User(Integer id) {
        this.id = id;
    }

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone", unique = true)
    private String userPhone;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "user_sex")
    private String userSex;

    @Column(name = "user_pass")
    private String userPass;

    private String avatar;

    @Column(name = "wechat_openid", unique = true)
    private String wechatOpenid;
}
