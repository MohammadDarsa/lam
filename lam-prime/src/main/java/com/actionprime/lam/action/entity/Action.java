package com.actionprime.lam.action.entity;

import com.actionprime.lam.action.type.Visibility;
import com.actionprime.lam.audit.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Entity class for Action.
 *
 * @author ActionPrime Team
 */
@Getter
@Setter
@Entity
@Table(name = "action")
public class Action extends Auditable {

    /**
     * Name of the action.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * Description of the action.
     */
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Visibility of the action.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private Visibility visibility;

    /**
     * Set of variables associated with the action.
     */
    @OneToMany(mappedBy = "action", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<Variable> variables = new LinkedHashSet<>();
}