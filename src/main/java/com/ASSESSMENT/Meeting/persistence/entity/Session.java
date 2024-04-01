package com.ASSESSMENT.Meeting.persistence.entity;

import jakarta.persistence.*;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Long sessionId;
    @Column(name = "session_name")
    private String sessionName;
    @Column(name = "session_description")
    private String sessionDescription;
    @Column(name = "session_schedule")
    @Temporal(TemporalType.DATE)
    private Date sessionSchedule;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    private Date updateAt;
    @OneToMany(mappedBy = "sessionId")
    private List<Attendees> attendees = new ArrayList<>();
    @Column(name = "session_duration")
    private Float sessionDuration;
    @Column(name = "is_active")
    private Boolean isActive;
    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public Date getSessionSchedule() {
        return sessionSchedule;
    }

    public void setSessionSchedule(Date sessionSchedule) {
        this.sessionSchedule = sessionSchedule;
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

    public Float getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(Float sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Attendees> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Attendees> attendees) {
        this.attendees = attendees;
    }
}
