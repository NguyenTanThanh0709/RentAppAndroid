package com.example.rentappandroid.Model;

import java.io.Serializable;

public class Message implements Serializable {
    private String landrodId;
    private String tenantId;
    private String content;
    private long timestamp;
    private String namet ;
    private String namel;

    public Message(String landrodId, String tenantId, String content, long timestamp, String namet, String namel) {
        this.landrodId = landrodId;
        this.tenantId = tenantId;
        this.content = content;
        this.timestamp = timestamp;
        this.namet = namet;
        this.namel = namel;
    }

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    public String getNamel() {
        return namel;
    }

    public void setNamel(String namel) {
        this.namel = namel;
    }

    public Message() {
    }


    @Override
    public String toString() {
        return "Message{" +
                "landrodId='" + landrodId + '\'' +
                ", tenantId='" + tenantId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", namet='" + namet + '\'' +
                ", namel='" + namel + '\'' +
                '}';
    }

    public String getLandrodId() {
        return landrodId;
    }

    public void setLandrodId(String landrodId) {
        this.landrodId = landrodId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Message( String landrodId, String tenantId, String content, long timestamp) {
        this.landrodId = landrodId;
        this.tenantId = tenantId;
        this.content = content;
        this.timestamp = timestamp;
    }
}
