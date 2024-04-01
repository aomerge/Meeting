package com.ASSESSMENT.Meeting.persistence.entity;

import jakarta.persistence.*;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "sub_name")
    private String subName;
    private String email;
    private String password;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @OneToMany(mappedBy = "userId")
    private List<Attendees> attendees = new ArrayList<>();

    public List<Attendees> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendees> attendees) {
        this.attendees = attendees;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
