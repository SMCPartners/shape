package com.smcpartners.shape.shared.dto.shape.request;

import java.util.Date;

/**
 * Responsible: Data transfer object for uploaded file.<br/>
 * 1. <br/>
 * <p>
 * Created by johndestefano on 5/9/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
public class FileUploadRequestDTO {
    private int fileUploadId;
    private String userId;
    private int measureEntityId;
    private Date uploadDt;
    private String uploadedB64File;

    public FileUploadRequestDTO() {
    }

    public FileUploadRequestDTO(String userId, int measureEntityId, Date uploadDt, String uploadedB64File) {
        this.userId = userId;
        this.measureEntityId = measureEntityId;
        this.uploadDt = uploadDt;
        this.uploadedB64File = uploadedB64File;
    }

    public int getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(int fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMeasureEntityId() {
        return measureEntityId;
    }

    public void setMeasureEntityId(int measureEntityId) {
        this.measureEntityId = measureEntityId;
    }

    public Date getUploadDt() {
        return uploadDt;
    }

    public void setUploadDt(Date uploadDt) {
        this.uploadDt = uploadDt;
    }

    public String getUploadedB64File() {
        return uploadedB64File;
    }

    public void setUploadedB64File(String uploadedB64File) {
        this.uploadedB64File = uploadedB64File;
    }
}
