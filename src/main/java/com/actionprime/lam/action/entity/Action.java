package com.actionprime.lam.action.entity;

import com.actionprime.lam.action.type.Visibility;
import com.actionprime.lam.audit.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "action")
public class Action extends Auditable {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private Visibility visibility;

    @OneToMany(mappedBy = "action", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Variable> variables = new LinkedHashSet<>();
}