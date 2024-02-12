package com.actionprime.lam.action.entity;

import com.actionprime.lam.audit.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "variable")
public class Variable extends Auditable {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "default_value")
    private String defaultValue;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Column(name = "modifier")
    private String modifier;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
}