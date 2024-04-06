package com.ASSESSMENT.Meeting.persistence.entity;

import com.ASSESSMENT.Meeting.config.service.ValidationService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    @CreatedDate
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateAt;
    @OneToMany(mappedBy = "sessionId")
    @JsonManagedReference("attendeesSession")
    private List<Attendees> attendees = new ArrayList<>();
    @Column(name = "session_duration")
    private Float sessionDuration;
    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "access")
    @Enumerated(EnumType.STRING)
    private AccessType accessType = AccessType.PUBLIC;

    @Column(name = "access-code")
    private String accessCode = new ValidationService().generarCodigoAcceso(22);

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    public void setAccessType(AccessType accessType) {
        this.accessType = accessType;
    }

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

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", sessionName='" + sessionName + '\'' +
                ", sessionDescription='" + sessionDescription + '\'' +
                ", sessionSchedule=" + sessionSchedule +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", attendees=" + attendees +
                ", sessionDuration=" + sessionDuration +
                ", isActive=" + isActive +
                '}';
    }
    @PrePersist
    public void prePersist() {
        createAt = new Date(System.currentTimeMillis());
        updateAt = new Date(System.currentTimeMillis());
    }
    public enum AccessType {
        PUBLIC,
        PRIVATE
    }
}
