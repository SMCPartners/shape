package com.smcpartners.shape.frameworks.data.entitymodel.shape;

import javax.persistence.*;
import java.util.Date;

/**
 * Responsible: Entity for saving measure upload information<br/>
 * 1. <br/>
 * <p>
 * Created by johndestefano on 5/9/16.
 * </p>
 * <p>
 * Changes:<br/>
 * 1. <br/>
 * </p>
 */
@Entity
@Table(name = "file_upload", schema = "", catalog = "shape")
public class FileUploadEntity {

    private int fileUploadId;
    private Date uploadDt;
    private String uploadedB64File;
    private UserEntity userByUserId;
    private MeasureEntity measureEntityByMeasureEntityId;



    public FileUploadEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fileUploadId", nullable = false, insertable = true, updatable = true)
    public int getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(int fileUploadId) {
        this.fileUploadId = fileUploadId;
    }

    @Basic
    @Column(name = "uploadDt", nullable = false, insertable = true, updatable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUploadDt() {
        return uploadDt;
    }

    public void setUploadDt(Date uploadDt) {
        this.uploadDt = uploadDt;
    }

    @Lob
    @Column(name = "uploadedB64File", nullable = true, insertable = true, updatable = true)
    public String getUploadedB64File() {
        return uploadedB64File;
    }

    public void setUploadedB64File(String uploadedB64File) {
        this.uploadedB64File = uploadedB64File;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "measure_id", referencedColumnName = "id", nullable = false)
    public MeasureEntity getMeasureEntityByMeasureEntityId() {
        return measureEntityByMeasureEntityId;
    }

    public void setMeasureEntityByMeasureEntityId(MeasureEntity measureByMeasureId) {
        this.measureEntityByMeasureEntityId = measureByMeasureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileUploadEntity that = (FileUploadEntity) o;

        if (fileUploadId != that.fileUploadId) return false;
        if (uploadDt != null ? !uploadDt.equals(that.uploadDt) : that.uploadDt != null) return false;
        if (uploadedB64File != null ? !uploadedB64File.equals(that.uploadedB64File) : that.uploadedB64File != null)
            return false;
        return userByUserId != null ? userByUserId.equals(that.userByUserId) : that.userByUserId == null;

    }

    @Override
    public int hashCode() {
        int result = fileUploadId;
        result = 31 * result + (uploadDt != null ? uploadDt.hashCode() : 0);
        result = 31 * result + (uploadedB64File != null ? uploadedB64File.hashCode() : 0);
        result = 31 * result + (userByUserId != null ? userByUserId.hashCode() : 0);
        return result;
    }
}
