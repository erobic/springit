package com.erobic.springit.entities;

import com.erobic.springit.utils.DateTimeUtil;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by robik on 1/1/17.
 */
@MappedSuperclass
public class ReferenceEntity implements Serializable{
    private static final long serialVersionUID = 1;
    @Id
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String label;
    private Integer ordinal;
    private LocalDateTime effectiveFrom;
    private LocalDateTime effectiveTill;
    @NotNull
    private LocalDateTime createdAt = DateTimeUtil.nowUTC();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public LocalDateTime getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDateTime effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDateTime getEffectiveTill() {
        return effectiveTill;
    }

    public void setEffectiveTill(LocalDateTime effectiveTill) {
        this.effectiveTill = effectiveTill;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
