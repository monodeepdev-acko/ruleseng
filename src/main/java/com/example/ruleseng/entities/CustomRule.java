package com.example.ruleseng.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "custom_rule")
@AllArgsConstructor
public class CustomRule {

    @Id
    private String id;

    @Column(unique = true)
    private String name;

    private String channel; // filter criteria : buy/endorsement/renewal

    private Boolean isActive;

    private String description;
    private int priority;

    private String condition; // easy rules

    private String action; // modify the context, or call setter methods

    private ZonedDateTime createdOn;
    private ZonedDateTime updatedOn;

    // created/modified on
    // created/modified by
}
