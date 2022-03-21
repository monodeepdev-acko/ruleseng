package com.example.ruleseng.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "custom_rule")
@AllArgsConstructor
public class CustomRule {

    @Id
    @Column(unique = true)
    private String name;
    private String channel;

    private String description;
    private int priority;
    private String condition;
    private String action;
}
