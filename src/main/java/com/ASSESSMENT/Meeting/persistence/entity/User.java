package com.ASSESSMENT.Meeting.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "sub_name")
    private String subName;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "create_at", nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date createAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "update_at", nullable = false)
    @LastModifiedDate
    private Date updateAt;

    @OneToMany(mappedBy = "userId")
    @JsonManagedReference("attendeesUser")
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", subName='" + subName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
    @PrePersist
    public void onCreate() {
        createAt = new Date();
        updateAt = new Date();
    }
}
