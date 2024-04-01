package com.ASSESSMENT.Meeting.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendees")
public class Attendees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendees_id")
    private Long attendeesId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "session_id", insertable = false, updatable = false)
    private Session sessionId;

    @Column(name = "is_active")
    private Boolean isActive;

    public Long getAttendeesId() {
        return attendeesId;
    }

    public void setAttendeesId(Long attendeesId) {
        this.attendeesId = attendeesId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Session getSessionId() {
        return sessionId;
    }

    public void setSessionId(Session sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
