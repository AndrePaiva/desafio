package com.cotec.desafio.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Organization extends BasicEntity {

    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Sector> sectors;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<Goal> goals;

    @Column
    private String description;

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
