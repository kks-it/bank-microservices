package com.bank.accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // mappings will apply only to its subclasses since no table exists for the mapped superclass itself.
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(updatable = false)  // column is included in SQL UPDATE statements
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(insertable = false)  // column is included in SQL INSERT statements
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(insertable = false)
    @LastModifiedBy
    private String updatedBy;
}
