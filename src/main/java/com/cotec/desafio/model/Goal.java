package com.cotec.desafio.model;

import com.cotec.desafio.model.serializer.BidirectionalSerialazerGoalToOrganization;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Goal extends BasicEntity {

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "organizationId")
    @JsonSerialize(using = BidirectionalSerialazerGoalToOrganization.class)
    @NotNull
    private Organization organization;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(columnDefinition = "goalId")
    private Goal goal;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private GoalType type;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GoalType getType() {
        return type;
    }

    public void setType(GoalType type) {
        this.type = type;
    }
}

