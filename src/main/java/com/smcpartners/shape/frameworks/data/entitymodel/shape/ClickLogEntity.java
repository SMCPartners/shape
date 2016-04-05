package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Date;

/**
 * Responsible:</br>
 * 1. Entity</br>
 * <p>
 * Created by johndestefano on 3/15/16.
 * </p>
 * <p>
 * Changes:</br>
 * 1. </br>
 * </p>
 */
@NamedQueries({
        @NamedQuery(name = "ClickLog.findByUser",
                query = "SELECT l FROM ClickLogEntity l WHERE l.userByUserId = :uId")
})
@Entity
@Table(name = "click_log", schema = "", catalog = "shape")
public class ClickLogEntity {
    private int clickLogId;
    private String event;
    private Date eventDt;
    private String additionalInfo;
    private String requestInfo;
    private String responseInfo;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clickLogId", nullable = false, insertable = true, updatable = true)
    public int getClickLogId() {
        return clickLogId;
    }

    public void setClickLogId(int clickLogId) {
        this.clickLogId = clickLogId;
    }

    @Basic
    @Column(name = "event", nullable = false, insertable = true, updatable = true, length = 100)
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Basic
    @Column(name = "eventDt", nullable = false, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEventDt() {
        return eventDt;
    }

    public void setEventDt(Date eventDt) {
        this.eventDt = eventDt;
    }

    @Basic
    @Column(name = "additionalInfo", nullable = true, insertable = true, updatable = true, length = 255)
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Lob
    @Column(name = "requestInfo", nullable = true, insertable = true, updatable = true)
    public String getRequestInfo() {
        return requestInfo;
    }

    public void setRequestInfo(String requestInfo) {
        this.requestInfo = requestInfo;
    }

    @Lob
    @Column(name = "responseInfo", nullable = true, insertable = true, updatable = true)
    public String getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(String responseInfo) {
        this.responseInfo = responseInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClickLogEntity that = (ClickLogEntity) o;

        if (clickLogId != that.clickLogId) return false;
        if (event != null ? !event.equals(that.event) : that.event != null) return false;
        if (eventDt != null ? !eventDt.equals(that.eventDt) : that.eventDt != null) return false;
        if (additionalInfo != null ? !additionalInfo.equals(that.additionalInfo) : that.additionalInfo != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clickLogId;
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (eventDt != null ? eventDt.hashCode() : 0);
        result = 31 * result + (additionalInfo != null ? additionalInfo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
