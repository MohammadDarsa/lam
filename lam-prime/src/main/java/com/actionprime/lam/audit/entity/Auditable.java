package com.actionprime.lam.audit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * MappedSuperclass that adds automatic auditing fields to entities.
 *
 * @author ActionPrime
 */
@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    /**
     * The unique identifier for the entity.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * The date and time the entity was created.
     */
    @CreatedDate
    @Column(updatable = false)
    protected LocalDateTime createdDate;

    /**
     * The date and time the entity was last modified.
     */
    @LastModifiedDate
    protected LocalDateTime lastModifiedDate;
}
