package com.ASSESSMENT.Meeting.config.StructureToken;

public class SessionDto {
    private Long sessionId;
    private String sessionName;
    private String sessionDescription;
    private float sessionDuration;

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

    public float getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(float sessionDuration) {
        this.sessionDuration = sessionDuration;
    }
}
