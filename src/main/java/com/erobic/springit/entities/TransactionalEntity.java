package com.erobic.springit.entities;

import com.erobic.springit.utils.DateTimeUtil;
import com.erobic.springit.utils.RequestContextUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by robik on 1/1/17.
 */
@MappedSuperclass
public class TransactionalEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String referenceId = UUID.randomUUID().toString();
    @Version
    private Integer version;
    @NotNull
    private String createdBy;
    @NotNull
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "TransactionalEntity{" +
                "id=" + id +
                ", referenceId='" + referenceId + '\'' +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionalEntity)) return false;

        TransactionalEntity that = (TransactionalEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @PrePersist
    public void prePersist() {
        setCreatedAt(DateTimeUtil.nowUTC());
        String username = RequestContextUtil.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Cannot persist a TransactionalEntity without a username!");
        }
        setCreatedBy(RequestContextUtil.getUsername());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(DateTimeUtil.nowUTC());
        String username = RequestContextUtil.getUsername();
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Cannot update a TransactionalEntity without a username!");
        }
        setUpdatedBy(RequestContextUtil.getUsername());
    }
}
