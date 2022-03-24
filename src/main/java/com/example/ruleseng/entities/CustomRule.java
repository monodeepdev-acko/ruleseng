package com.example.ruleseng.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "custom_rule")
@NoArgsConstructor
public class CustomRule {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    private String channel; // filter criteria : buy/endorsement/renewal

    private Boolean isActive;

    private String description;
    private int priority;

    @NotNull
    @Column(nullable = false)
    private String condition; // easy rules

    @NotNull
    @Column(nullable = false)
    private String action; // modify the context, or call setter methods

    @CreationTimestamp
    private ZonedDateTime createdOn;

    @UpdateTimestamp
    private ZonedDateTime updatedOn;

    // created/modified on
    // created/modified by
}
