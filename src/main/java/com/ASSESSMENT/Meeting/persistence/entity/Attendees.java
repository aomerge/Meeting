package com.ASSESSMENT.Meeting.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
/**
 * This class represents the Attendees entity
 */
@Entity
@Table(name = "attendees")
public class Attendees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendees_id")
    private Long attendeesId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference("attendeesUser")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "session_id", referencedColumnName = "session_id")
    @JsonBackReference("attendeesSession")
    private Session sessionId;

    @Column(name = "access")
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

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


    @Override
    public String toString() {
        return "Attendees{" +
                "attendeesId=" + attendeesId +
                ", userId=" + userId +
                ", sessionId=" + sessionId +
                ", isActive=" +  +
                '}';
    }
    public enum AccessType {
        OWNER,
        ATTENDEE
    }
}
